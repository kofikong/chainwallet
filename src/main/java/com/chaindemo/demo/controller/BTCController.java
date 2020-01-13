package com.chaindemo.demo.controller;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.util.TextUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chaindemo.demo.constant.MyConstants;
import com.chaindemo.demo.model.BTCInfo;
import com.chaindemo.demo.model.BlockHashInfo;
import com.chaindemo.demo.model.BlockHeightInfo;
import com.chaindemo.demo.net.SslUtil;

import io.cryptoapis.blockchains.bitcoin_based.services.BlockService;
import io.cryptoapis.client.CryptoApis;
import io.cryptoapis.common_models.ApiResponse;
import io.cryptoapis.connections.Bitcoin;
import io.cryptoapis.utils.constants.CryptoApisConstants;

@RestController
public class BTCController {
	@RequestMapping("/btc/info2")
    public BlockHashInfo dealInfo2(@RequestParam(value="name", defaultValue="Guest") String name, @RequestParam(value="pwd",defaultValue="123") String pwd, @RequestParam(value="hash",defaultValue="") String hash) {
    	if(!"Guest".equals(name) || !"123".equals(pwd)) {
    		return null;
    	}
    	
    	if(TextUtils.isEmpty(hash)) {
    		return null;
    	}
    	
		CryptoApis caClient = new CryptoApis(MyConstants.APIKEY);
//    	
		final Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.MAINNET);
		final BlockService bchBlockService = btc.getBlockService();
		final String blockHash = hash;
		ApiResponse res = bchBlockService.getBlock(blockHash);
		String resTmp = res.getResponse();
        return BlockHashInfo.Parse(resTmp);
    }
	
    @RequestMapping("/btc/info")
    public BTCInfo dealInfo(@RequestParam(value="name", defaultValue="Guest") String name, @RequestParam(value="pwd",defaultValue="123") String pwd) {
    	
//		CryptoApis caClient = new CryptoApis(APIKEY);
//    	
//		final Bitcoin btc = caClient.connectToBtc(CryptoApisConstants.MAINNET);
//		
		
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("X-API-Key", MyConstants.APIKEY);
		String res = null;
		try {
			res = SslUtil.Get("https://api.cryptoapis.io/v1/bc/btc/mainnet/info", headers, null, "UTF-8");
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
        return BTCInfo.Parse(res);
    }
    
    @RequestMapping("/btc/blockhash")
    public BlockHashInfo dealBlockHashInfo(@RequestParam(value="hash", defaultValue="") String hash) {
		Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("X-API-Key", MyConstants.APIKEY);
		String res = null;
		try {
			res = SslUtil.Get("https://api.cryptoapis.io/v1/bc/btc/mainnet/blocks/"+hash, headers, null, "UTF-8");
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
        return BlockHashInfo.Parse(res);
    }

    @RequestMapping("/btc/blockheight")
    public BlockHeightInfo dealBlockHeightInfo(@RequestParam(value="height", defaultValue="") String height) {
    	Map<String,String> headers = new HashMap<String,String>();
		headers.put("Content-Type", "application/json");
		headers.put("X-API-Key", MyConstants.APIKEY);
		String res = null;
		try {
			res = SslUtil.Get("https://api.cryptoapis.io/v1/bc/btc/mainnet/blocks/"+height, headers, null, "UTF-8");
		} catch (KeyManagementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
        return BlockHeightInfo.Parse(res);
    }
}
