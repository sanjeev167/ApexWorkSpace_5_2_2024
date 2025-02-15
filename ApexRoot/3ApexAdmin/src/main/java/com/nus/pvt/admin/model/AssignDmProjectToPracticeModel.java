package com.nus.pvt.admin.model;

import java.util.Date;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 07-Feb-2025<br>
 * @Time: 6:02:13 am<br>
 * @Objective: <br>
 */
public class AssignDmProjectToPracticeModel extends BaseModel{

	private int userTypeId;
	private int userId;
	private int projectAssignedToDmId;	
	private Date assignedOn;
	public AssignDmProjectToPracticeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AssignDmProjectToPracticeModel(int userTypeId, int userId, int projectAssignedToDmId, Date assignedOn) {
		super();
		this.userTypeId = userTypeId;
		this.userId = userId;
		this.projectAssignedToDmId = projectAssignedToDmId;
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
	 * @return the projectAssignedToDmId
	 */
	public int getProjectAssignedToDmId() {
		return projectAssignedToDmId;
	}
	/**
	 * @param projectAssignedToDmId the projectAssignedToDmId to set
	 */
	public void setProjectAssignedToDmId(int projectAssignedToDmId) {
		this.projectAssignedToDmId = projectAssignedToDmId;
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
