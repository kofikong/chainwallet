package com.chaindemo.demo.model;

import java.io.Serializable;

public class AddressInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8915698660480704190L;
	private String address;
	private String path;
	private String balance;

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getPath() {
		return path;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBalance() {
		return balance;
	}
}