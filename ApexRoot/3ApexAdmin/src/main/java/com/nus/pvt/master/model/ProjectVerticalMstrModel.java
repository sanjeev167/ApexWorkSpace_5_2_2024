package com.nus.pvt.master.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 9:30:44 pm<br>
 * @Objective: <br>
 */
public class ProjectVerticalMstrModel extends BaseModel {

	private String verticalName ;

	public ProjectVerticalMstrModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the verticalName
	 */
	public String getVerticalName() {
		return verticalName;
	}

	/**
	 * @param verticalName the verticalName to set
	 */
	public void setVerticalName(String verticalName) {
		this.verticalName = verticalName;
	}
	
	
}
