package com.nus.pvt.master.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 12:53:43 pm<br>
 * @Objective: <br>
 */
public class UserGradeModel extends BaseModel {

	private String userGrade;

	
	
	public UserGradeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the userType
	 */
	public String getUserGrade() {
		return userGrade;
	}

	/**
	 * @param userType the userType to set
	 */
	public void setUserGrade(String userGrade) {
		this.userGrade = userGrade;
	}
	
	
}
