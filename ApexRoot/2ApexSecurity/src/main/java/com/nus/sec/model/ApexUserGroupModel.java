package com.nus.sec.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 8:49:30 pm<br>
 * @Objective: <br>
 */
public class ApexUserGroupModel extends BaseModel{
	private Integer    apexUserId ;
	 private Integer    apexGroupId ;
	 
	 
	public ApexUserGroupModel() {
		super();		
	}


	public Integer getApexUserId() {
		return apexUserId;
	}


	public void setApexUserId(Integer apexUserId) {
		this.apexUserId = apexUserId;
	}


	public Integer getApexGroupId() {
		return apexGroupId;
	}


	public void setApexGroupId(Integer apexGroupId) {
		this.apexGroupId = apexGroupId;
	}
	
	
	 
}//End of ApexUserGroupModel

