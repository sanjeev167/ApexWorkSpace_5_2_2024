package com.nus.sec.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:33:03 pm<br>
 * @Objective: <br>
 */
public class ApexGroupRoleModel  extends BaseModel{
	
	 private Integer    apexGroupId ;
	 private Integer    apexRoleId ;	 
	 
	 
	public ApexGroupRoleModel() {
		super();		
	}


	public Integer getApexGroupId() {
		return apexGroupId;
	}


	public void setApexGroupId(Integer apexGroupId) {
		this.apexGroupId = apexGroupId;
	}


	public Integer getApexRoleId() {
		return apexRoleId;
	}


	public void setApexRoleId(Integer apexRoleId) {
		this.apexRoleId = apexRoleId;
	}
	
	
	
	 
}//End of ApiRoleGroupModel

