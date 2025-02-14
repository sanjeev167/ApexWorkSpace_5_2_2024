package com.nus.pvt.admin.model;

import java.util.Date;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 07-Feb-2025<br>
 * @Time: 5:25:33 am<br>
 * @Objective: <br>
 */
public class AssignPmProjectToDmModel extends BaseModel{
	private int userTypeId;
	private int userId;
	private int projectAssignedToPmId;	
	private Date assignedOn;
	
	public AssignPmProjectToDmModel() {
		super();
	}
	
	public AssignPmProjectToDmModel(int userTypeId, int userId, int projectAssignedToPmId, Date assignedOn) {
		super();
		this.userTypeId = userTypeId;
		this.userId = userId;
		this.projectAssignedToPmId = projectAssignedToPmId;
		this.assignedOn = assignedOn;
	}
	/**
	 * @return the userTypeId
	 */
	public int getUserTypeId() {
		return userTypeId;
	}
	/**
	 * @param userTypeId the userTypeId to set
	 */
	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}
	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}
	/**
	 * @param userId the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}
	/**
	 * @return the projectAssignedToPmId
	 */
	public int getProjectAssignedToPmId() {
		return projectAssignedToPmId;
	}
	/**
	 * @param projectAssignedToPmId the projectAssignedToPmId to set
	 */
	public void setProjectAssignedToPmId(int projectAssignedToPmId) {
		this.projectAssignedToPmId = projectAssignedToPmId;
	}
	/**
	 * @return the assignedOn
	 */
	public Date getAssignedOn() {
		return assignedOn;
	}
	/**
	 * @param assignedOn the assignedOn to set
	 */
	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}
	
	
}
	