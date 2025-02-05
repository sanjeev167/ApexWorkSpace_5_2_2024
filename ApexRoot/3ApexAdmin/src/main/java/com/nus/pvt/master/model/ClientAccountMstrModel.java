package com.nus.pvt.master.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 8:18:12 pm<br>
 * @Objective: <br>
 */
public class ClientAccountMstrModel extends BaseModel{

	private String clientAccount;	

	public ClientAccountMstrModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the clientAccount
	 */
	public String getClientAccount() {
		return clientAccount;
	}

	/**
	 * @param clientAccount the clientAccount to set
	 */
	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}
	
	
	
}
