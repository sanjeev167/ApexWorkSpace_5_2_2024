package com.nus.pvt.master.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 9:11:52 pm<br>
 * @Objective: <br>
 */
public class ProjectTypeMstrModel extends BaseModel{

	private String projectType ;

	public ProjectTypeMstrModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the projectType
	 */
	public String getProjectType() {
		return projectType;
	}

	/**
	 * @param projectType the projectType to set
	 */
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	
	
	
}
