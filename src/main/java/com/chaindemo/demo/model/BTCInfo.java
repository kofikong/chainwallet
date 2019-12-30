package com.chaindemo.demo.model;

import java.io.Serializable;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;


public class BTCInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6190233378978531793L;
	private String difficulty;
	private String headers;
	private String chain;
	private String chainWork;
	private String mediantime;
	private String blocks;
	private String bestBlockHash;
	private String currency;
	private String transactions;
	private String verificationProgress;
	
	
	public String getDifficulty() {
		return difficulty;
	}


	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}


	public String getHeaders() {
		return headers;
	}


	public void setHeaders(String headers) {
		this.headers = headers;
	}


	public String getChain() {
		return chain;
	}


	public void setChain(String chain) {
		this.chain = chain;
	}


	public String getChainWork() {
		return chainWork;
	}


	public void setChainWork(String chainWork) {
		this.chainWork = chainWork;
	}


	public String getMediantime() {
		return mediantime;
	}


	public void setMediantime(String mediantime) {
		this.mediantime = mediantime;
	}


	public String getBlocks() {
		return blocks;
	}


	public void setBlocks(String blocks) {
		this.blocks = blocks;
	}


	public String getBestBlockHash() {
		return bestBlockHash;
	}


	public void setBestBlockHash(String bestBlockHash) {
		this.bestBlockHash = bestBlockHash;
	}


	public String getCurrency() {
		return currency;
	}


	public void setCurrency(String currency) {
		this.currency = currency;
	}


	public String getTransactions() {
		return transactions;
	}


	public void setTransactions(String transactions) {
		this.transactions = transactions;
	}


	public String getVerificationProgress() {
		return verificationProgress;
	}


	public void setVerificationProgress(String verificationProgress) {
		this.verificationProgress = verificationProgress;
	}


	public static BTCInfo Parse(String res) {
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
				BTCInfo obj = (BTCInfo) JSONObject.toBean(jsonObj, BTCInfo.class);
				return obj;
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
