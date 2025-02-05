package com.nus.sec.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:23:38 pm<br>
 * @Objective: <br>
 */
public class ApexGroupModel extends BaseModel{
	
	
	private String groupName;

	public ApexGroupModel() {
		super();
	}


	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

}// End of ApexGroupModel
