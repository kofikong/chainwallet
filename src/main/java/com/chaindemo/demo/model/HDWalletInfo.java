package com.chaindemo.demo.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class HDWalletInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7284536764846015830L;
	private String walletName;
    private ArrayList<AddressInfo> addresses;
    private String totalBalance;
    public void setWalletName(String walletName) {
         this.walletName = walletName;
     }
     public String getWalletName() {
         return walletName;
     }

    public void setAddresses(ArrayList<AddressInfo> addresses) {
         this.addresses = addresses;
     }
     public List<AddressInfo> getAddresses() {
         return addresses;
     }

    public void setTotalBalance(String totalBalance) {
         this.totalBalance = totalBalance;
     }
     public String getTotalBalance() {
         return totalBalance;
     }

	public static HDWalletInfo Parse(String res) {
		/*
		 * { "payload": { "walletName": "\"demoWallet\"", "addresses": [{ "address":
		 * "14V9MQM6sPbuT5DUwQcUiGeYraadK5GCLh", "path": "M/0H/0/0" }, { "address":
		 * "17y5MwaazHzsJyJiuRcTignFaqSqarZbac", "path": "M/0H/0/1" }, { "address":
		 * "19XQjag23cnDmUxf8KSNMNg3yYG65jYMAT", "path": "M/0H/0/2" }, { "address":
		 * "1MtwKAEcSsGtPQJnTMDi9wD6779UwJpYjZ", "path": "M/0H/0/3" }, { "address":
		 * "1MywRNhMGuchsHgX2WUAPnpvr1EqCrJXnr", "path": "M/0H/0/4" }] } }
		 */

		if (res == null) {
			return null;
		}

		try {
			JSONObject objPayload = JSONObject.fromObject(res);
			if (objPayload.has("payload")) {
				JSONObject jsonObj = objPayload.getJSONObject("payload");
				HDWalletInfo obj = (HDWalletInfo) JSONObject.toBean(jsonObj, HDWalletInfo.class);
				return obj;
			}

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
}
