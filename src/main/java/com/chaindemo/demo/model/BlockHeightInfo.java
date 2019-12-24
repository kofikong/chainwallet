package com.chaindemo.demo.model;

import java.util.Date;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

/**
 * Auto-generated: 2019-12-23 0:18:54
 *
 * @author kongwenchao
 */
public class BlockHeightInfo {

    private String hash;
    private long strippedsize;
    private long size;
    private long weight;
    private long height;
    private long version;
    private String versionHex;
    private String merkleroot;
    private Date time;
    private Date datetime;
    private Date mediantime;
    private long nonce;
    private String bits;
    private double difficulty;
    private String chainwork;
    private String previousblockhash;
    private String nextblockhash;
    private int transactions;
    private List<String> tx;
    private int confirmations;
    private long timestamp;
    public void setHash(String hash) {
         this.hash = hash;
     }
     public String getHash() {
         return hash;
     }

    public void setStrippedsize(long strippedsize) {
         this.strippedsize = strippedsize;
     }
     public long getStrippedsize() {
         return strippedsize;
     }

    public void setSize(long size) {
         this.size = size;
     }
     public long getSize() {
         return size;
     }

    public void setWeight(long weight) {
         this.weight = weight;
     }
     public long getWeight() {
         return weight;
     }

    public void setHeight(long height) {
         this.height = height;
     }
     public long getHeight() {
         return height;
     }

    public void setVersion(long version) {
         this.version = version;
     }
     public long getVersion() {
         return version;
     }

    public void setVersionHex(String versionHex) {
         this.versionHex = versionHex;
     }
     public String getVersionHex() {
         return versionHex;
     }

    public void setMerkleroot(String merkleroot) {
         this.merkleroot = merkleroot;
     }
     public String getMerkleroot() {
         return merkleroot;
     }

    public void setTime(Date time) {
         this.time = time;
     }
     public Date getTime() {
         return time;
     }

    public void setDatetime(Date datetime) {
         this.datetime = datetime;
     }
     public Date getDatetime() {
         return datetime;
     }

    public void setMediantime(Date mediantime) {
         this.mediantime = mediantime;
     }
     public Date getMediantime() {
         return mediantime;
     }

    public void setNonce(long nonce) {
         this.nonce = nonce;
     }
     public long getNonce() {
         return nonce;
     }

    public void setBits(String bits) {
         this.bits = bits;
     }
     public String getBits() {
         return bits;
     }

    public void setDifficulty(double difficulty) {
         this.difficulty = difficulty;
     }
     public double getDifficulty() {
         return difficulty;
     }

    public void setChainwork(String chainwork) {
         this.chainwork = chainwork;
     }
     public String getChainwork() {
         return chainwork;
     }

    public void setPreviousblockhash(String previousblockhash) {
         this.previousblockhash = previousblockhash;
     }
     public String getPreviousblockhash() {
         return previousblockhash;
     }

    public void setNextblockhash(String nextblockhash) {
         this.nextblockhash = nextblockhash;
     }
     public String getNextblockhash() {
         return nextblockhash;
     }

    public void setTransactions(int transactions) {
         this.transactions = transactions;
     }
     public int getTransactions() {
         return transactions;
     }

    public void setTx(List<String> tx) {
         this.tx = tx;
     }
     public List<String> getTx() {
         return tx;
     }

    public void setConfirmations(int confirmations) {
         this.confirmations = confirmations;
     }
     public int getConfirmations() {
         return confirmations;
     }

    public void setTimestamp(long timestamp) {
         this.timestamp = timestamp;
     }
     public long getTimestamp() {
         return timestamp;
     }

     public static BlockHeightInfo Parse(String res) {
  		/*
  		{
		  "payload": {
		      "hash": "00000000000000000009917a263a01b80d3cbe6e69b562a29d62de9170551be8",
		      "strippedsize": 909570,
		      "size": 1264413,
		      "weight": 3993123,
		      "height": 564349,
		      "version": 536870912,
		      "versionHex": "20000000",
		      "merkleroot": "95439d11e918c9fd9a901dcf22203d60f538d660ae74efb7cb566825420fd3b7",
		      "time": "2019-02-23 17:33:30 UTC", 
		      "datetime": "2019-02-23 17:33:30 UTC",
		      "mediantime": "2019-02-23 16:53:23 UTC",
		      "nonce": 117587101,
		      "bits": "172e6f88",
		      "difficulty": 6061518831027.271,
		      "chainwork": "00000000000000000000000000000000000000000532b1d8837e5d34f183a10a",
		      "previousblockhash": "0000000000000000002b4efdd796d0288213b7d2f2849ec95e9be9a9a722e5e6",
		      "nextblockhash": "0000000000000000002aa120a6e7200a6a726f15b793b41868acf2a7b24f1850",
		      "transactions": 3004,
		      "tx":[
		          "347d96855d41b77f1e23048fff11c18e9fe699ee69b0b402338f34189734e0a2",
		          ...
		        "6bb1b2dc66d2dde53aebcebb8ecef19a69f77322dab2fdbc8e6088bfbda13649"
		      ],
		      "confirmations": 1,
		      "timestamp": 1550943210
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
  				BlockHeightInfo obj = (BlockHeightInfo) JSONObject.toBean(jsonObj, BlockHeightInfo.class);
  				return obj;
  			}
  			
  		} catch (JSONException e) {
  			// TODO Auto-generated catch block
  			e.printStackTrace();
  		}
  		
  		return null;
  	}
}