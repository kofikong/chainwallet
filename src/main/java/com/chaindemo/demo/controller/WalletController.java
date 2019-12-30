package com.chaindemo.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.TextUtils;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaindemo.demo.model.HDWalletInfo;
import com.chaindemo.demo.model.WalletInfo;
import com.chaindemo.demo.utils.MyConstants;

import io.cryptoapis.blockchains.bitcoin_based.models.Transaction.CreateTransaction;
import io.cryptoapis.blockchains.bitcoin_based.services.TransactionService;
import io.cryptoapis.blockchains.bitcoin_based.services.WalletService;
import io.cryptoapis.client.CryptoApis;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.connections.Bitcoin;
import io.cryptoapis.connections.Bitcoin_Cash;
import io.cryptoapis.utils.constants.CryptoApisConstants;
//import org.json.JSONObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.util.JSONUtils;

@RestController
public class WalletController {

	private Bitcoin connect(String net) {
		CryptoApis caClient = new CryptoApis(MyConstants.APIKEY);
		final Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.MAINNET.equals(net) ? CryptoApisConstants.MAINNET : CryptoApisConstants.TESTNET);
		return btc;
	}
	@RequestMapping(value="/wallet/create",method= RequestMethod.GET)
	public WalletInfo dealWalletCreate(@RequestParam(value = "walletName", defaultValue = "") String walletName, @RequestParam(value="net", defaultValue="mainnet") String net) {

		if (TextUtils.isEmpty(walletName)) {
			return null;
		}

		final Bitcoin btc = connect(net);
		final WalletService ltcWalletService = btc.getWalletService();

		final List<String> addresses = new ArrayList<String>() {
			{
				add("LLrxRzrNVxyVQ2DjuMoCNEMN2YM2nkwr1K");
				add("LiLGYuVLV4HExGYizwZEjTpTG2apN4or8M");
				add("LP3LrpZDjCDhysQarq4STG97sH8a8Yf748");
			}
		};

		ApiResponse res = ltcWalletService.createWallet(addresses, walletName);
		String resTmp = res.getResponse();

		return WalletInfo.Parse(resTmp);
	}
	
	@RequestMapping("/hdwallet/create")
	public JSONObject dealDHWalletCreate(@RequestParam(value= "walletName", defaultValue = "") String walletName, @RequestParam(value = "password", defaultValue="")String password, @RequestParam(value="addressCount", defaultValue = "1") int addressCount, @RequestParam(value="net", defaultValue="mainnet") String net) {
		if(StringUtils.isEmpty(walletName)  || StringUtils.isEmpty(password) || addressCount < 1) {
			return null;
		}
		
		final Bitcoin btc = connect(net);
		final WalletService btcWalletService = btc.getWalletService();
		final int count = addressCount;
		final String name = walletName;
		final String pwd = password;
		ApiResponse res = btcWalletService.createHDWallet(name, addressCount, pwd);
		String resStr = res.getResponse();
		return JSONObject.fromObject(resStr);
	}
	
	@RequestMapping(value="/hdwallet/info",method= RequestMethod.GET)
	public JSONObject dealDHWalletInfo(@RequestParam(value= "walletName", defaultValue = "") String walletName, @RequestParam(value="net", defaultValue="mainnet") String net) {
		if(StringUtils.isEmpty(walletName)) {
			return null;
		}
		
		final Bitcoin btc = connect(net);
		final WalletService btcWalletService = btc.getWalletService();
		final String name = walletName;
		ApiResponse res = btcWalletService.getHDWallet(name);
		String resStr = res.getResponse();
//		HDWalletInfo info = HDWalletInfo.Parse(resStr); 
		JSONObject obj = JSONObject.fromObject(resStr);
		return obj;
	}
	
	@RequestMapping("/hdwallet/append")
	public HDWalletInfo dealDHWalletInfo(@RequestParam(value= "walletName", defaultValue = "") String walletName, @RequestParam(value = "password", defaultValue="")String password, @RequestParam(value="addressCount", defaultValue = "1") int addressCount, @RequestParam(value="net", defaultValue="mainnet") String net) {
		if(StringUtils.isEmpty(walletName)) {
			return null;
		}
		
		final Bitcoin btc = connect(net);
		final WalletService btcWalletService = btc.getWalletService();
		final int count = addressCount;
		final String name = walletName;
		final String pwd = password;
		ApiResponse res = btcWalletService.generateAddressHDWallet(name,count,pwd);
		String resStr = res.getResponse();
		HDWalletInfo info = HDWalletInfo.Parse(resStr); 
		return info;
	}
	
	@RequestMapping("/hdwallet/delete")
	public JSONObject deleteDHWalletInfo(@RequestParam(value= "walletName", defaultValue = "") String walletName, @RequestParam(value="net", defaultValue="mainnet") String net) {
		if(StringUtils.isEmpty(walletName)) {
			return null;
		}
		
		final Bitcoin btc = connect(net);
		final WalletService btcWalletService = btc.getWalletService();
		final String name = walletName;
		ApiResponse res = btcWalletService.deleteHDWallet(name);
		String resStr = res.getResponse();
		JSONObject info = JSONObject.fromObject(resStr); 
		return info;
	}
	
	@RequestMapping("/hdwallet/transaction/create")
	public JSONObject createTransactionUsingDHWallet(@RequestParam(value= "walletName", defaultValue = "") String walletName,@RequestParam(value = "password", defaultValue="")String password, @RequestParam(value="address", defaultValue="") String address, @RequestParam(value="net", defaultValue="mainnet") String net) {
		if(StringUtils.isEmpty(walletName) || StringUtils.isEmpty(password) || StringUtils.isEmpty(address)) {
			return null;
		}
		
		final Bitcoin btc = connect(net);
		final TransactionService transactionService = btc.getTransactionService();

		List<CreateTransaction.Outputs> outputs = new ArrayList<>();
		CreateTransaction.Outputs output = new CreateTransaction.Outputs();
//		output.setAddress("my4TmbbhJCLJB9q1eHUHQWJfbbJoYdLwtE");
		output.setAddress(address);
		output.setValue(0.00001);
		outputs.add(output);

		CreateTransaction.Fee fee = new CreateTransaction.Fee();
		fee.setValue(0.00001);

		ApiResponse response = transactionService.newTxWithHDWallet(walletName, password, null, outputs, fee, 0);
		String resStr = response.getResponse();
		JSONObject obj = JSONObject.fromObject(resStr);
		return obj;
		
		/*
		http://localhost:8080/hdwallet/transaction/create?walletName=demoWallet&password=1234567890&address=mmHwSdXoZqKfb71seeY9zRVPFd6nLrK26e&net=testnet
		{
			payload:- {
			txid: "46e284d0cd76c6cbe26d59e2eccc25c4177fc49490dbbd8fdc04607beab8cc00",
			view_in_explorer: "https://blockexplorer.one/btc/testnet/tx/46e284d0cd76c6cbe26d59e2eccc25c4177fc49490dbbd8fdc04607beab8cc00?utm_source=cryptoapis.io"
			}
		}
		
		*/
	}
	
	@RequestMapping("/hdwallet/extendkey")
	public JSONObject dealDHWalletExtendedKey(@RequestParam(value = "password", defaultValue="")String password, @RequestParam(value="net", defaultValue="mainnet") String net) {
		if(StringUtils.isEmpty(password)) {
			return null;
		}
		
		final Bitcoin btc = connect(net);
		final WalletService btcWalletService = btc.getWalletService();
		ApiResponse res = btcWalletService.createExtendedKey(password);
		String resStr = res.getResponse();
		JSONObject info = JSONObject.fromObject(resStr); 
		return info;
	}
	
	
	@RequestMapping("/hdwallet/list")
	public JSONObject getDHWalletList(@RequestParam(value="net", defaultValue="mainnet") String net) {
		
		final Bitcoin btc = connect(net);
		final WalletService btcWalletService = btc.getWalletService();
	
		ApiResponse res = btcWalletService.listHDWallets();
		String resStr = res.getResponse();
		JSONObject info = JSONObject.fromObject(resStr); 
		return info;
	}
	
	
	
	
	
}