package com.chaindemo.demo.model;

import java.io.Serializable;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class WalletInfo implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8067850245806162847L;

	public static WalletInfo Parse(String res) {
		/*
		{
		    "payload": {
		        "difficulty": 7182852313938.317,
		        "headers": 546904,
		        "chain": "main",
		        "chainWork": "000000000000000000000000000000000000000003b8fe71a1bf647effbc862f",
		        "mediantime": 1540234846,
		        "blocks": 546904,
		        "bestBlockHash": "0000000000000000001c940339b65f3c1d85006041e9602bc9bda2c495e2ca82",
		        "currency": "BTC",
		        "transactions": 342149347,
		        "verificationProgress": 0.9999964749471614
		    }
		}
		 * */
		
		if(res == null) {
			return null;
		}
		
		try {
			JSONObject objPayload = JSONObject.fromObject(res);
			if(objPayload.has("payload")) {
				JSONObject jsonObj = objPayload.getJSONObject("payload");
				WalletInfo obj = (WalletInfo) JSONObject.toBean(jsonObj, WalletInfo.class);
				return obj;
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
