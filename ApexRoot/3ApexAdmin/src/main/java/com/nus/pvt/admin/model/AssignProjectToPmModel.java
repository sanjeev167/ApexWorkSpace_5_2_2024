package com.nus.pvt.admin.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @String: 07-Feb-2025<br>
 * @Time: 4:36:34 am<br>
 * @Objective: <br>
 */
public class AssignProjectToPmModel extends BaseModel{
	@JsonIgnore
	private int userGradeId;
	@JsonIgnore
	private int userId;
	@JsonIgnore
	private int clientAccountId;
	@JsonIgnore
	private int projectCodeId;
	@JsonIgnore
	private int projectVerticalId;
	@JsonIgnore
	private int projectTypeId;	
	@JsonIgnore
	private Date assignedOn;
	
	private String clientAccount;
	private String projectVertical;
	private String projectCode;
	private String projectName;
	private String projectType;
	private String assignedon;
	private String projectManagerName;
	private String empGrade;
	
	
	public AssignProjectToPmModel() {
		super();
		// TODO Auto-generated constructor stub
	}	
	
	public AssignProjectToPmModel(int userGradeId, int userId, int clientAccountId, int projectCodeId,
			int projectVerticalId, int projectTypeId, Date assignedOn) {
		super();
		this.userGradeId = userGradeId;
		this.userId = userId;
		this.clientAccountId = clientAccountId;
		this.projectCodeId = projectCodeId;
		this.projectVerticalId = projectVerticalId;
		this.projectTypeId = projectTypeId;
		this.assignedOn = assignedOn;
	}
	
	public AssignProjectToPmModel(Date assignedOn) {
		this.assignedOn = assignedOn;
	}

	/**
	 * @return the userGradeId
	 */
	public int getUserGradeId() {
		return userGradeId;
	}

	/**
	 * @param userGradeId the userGradeId to set
	 */
	public void setUserGradeId(int userGradeId) {
		this.userGradeId = userGradeId;
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
	 * @return the clientAccountId
	 */
	public int getClientAccountId() {
		return clientAccountId;
	}

	/**
	 * @param clientAccountId the clientAccountId to set
	 */
	public void setClientAccountId(int clientAccountId) {
		this.clientAccountId = clientAccountId;
	}

	/**
	 * @return the projectCodeId
	 */
	public int getProjectCodeId() {
		return projectCodeId;
	}

	/**
	 * @param projectCodeId the projectCodeId to set
	 */
	public void setProjectCodeId(int projectCodeId) {
		this.projectCodeId = projectCodeId;
	}

	/**
	 * @return the projectVerticalId
	 */
	public int getProjectVerticalId() {
		return projectVerticalId;
	}

	/**
	 * @param projectVerticalId the projectVerticalId to set
	 */
	public void setProjectVerticalId(int projectVerticalId) {
		this.projectVerticalId = projectVerticalId;
	}

	/**
	 * @return the projectTypeId
	 */
	public int getProjectTypeId() {
		return projectTypeId;
	}

	/**
	 * @param projectTypeId the projectTypeId to set
	 */
	public void setProjectTypeId(int projectTypeId) {
		this.projectTypeId = projectTypeId;
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

	/**
	 * @return the projectVertical
	 */
	public String getProjectVertical() {
		return projectVertical;
	}

	/**
	 * @param projectVertical the projectVertical to set
	 */
	public void setProjectVertical(String projectVertical) {
		this.projectVertical = projectVertical;
	}

	/**
	 * @return the projectCode
	 */
	public String getProjectCode() {
		return projectCode;
	}

	/**
	 * @param projectCode the projectCode to set
	 */
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}

	/**
	 * @return the projectName
	 */
	public String getProjectName() {
		return projectName;
	}

	/**
	 * @param projectName the projectName to set
	 */
	public void setProjectName(String projectName) {
		this.projectName = projectName;
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

	/**
	 * @return the assignedon
	 */
	public String getAssignedon() {
		return assignedon;
	}

	/**
	 * @param assignedon the assignedon to set
	 */
	public void setAssignedon(String assignedon) {
		this.assignedon = assignedon;
	}

	/**
	 * @return the projectManagerName
	 */
	public String getProjectManagerName() {
		return projectManagerName;
	}

	/**
	 * @param projectManagerName the projectManagerName to set
	 */
	public void setProjectManagerName(String projectManagerName) {
		this.projectManagerName = projectManagerName;
	}

	/**
	 * @return the empGrade
	 */
	public String getEmpGrade() {
		return empGrade;
	}

	/**
	 * @param empGrade the empGrade to set
	 */
	public void setEmpGrade(String empGrade) {
		this.empGrade = empGrade;
	}

	
	
}//End of AssignProjectToPmModel
