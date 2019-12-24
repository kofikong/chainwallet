package com.chaindemo.demo.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaindemo.demo.model.WalletInfo;
import com.chaindemo.demo.utils.MyConstants;

import io.cryptoapis.blockchains.bitcoin_based.services.WalletService;
import io.cryptoapis.client.CryptoApis;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.connections.Bitcoin;
import io.cryptoapis.utils.constants.CryptoApisConstants;

@RestController
public class WalletController {

	@RequestMapping("/wallet/create")
	public WalletInfo dealWalletCreate(@RequestParam(value = "walletName", defaultValue = "") String walletName) {

		if (TextUtils.isEmpty(walletName)) {
			return null;
		}

		CryptoApis caClient = new CryptoApis(MyConstants.APIKEY);
//    	
		final Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.MAINNET);
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
}
