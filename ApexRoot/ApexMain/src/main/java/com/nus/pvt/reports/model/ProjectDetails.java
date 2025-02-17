package com.nus.pvt.reports.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Feb-2025<br>
 * @Time: 12:28:27 am<br>
 * @Objective: <br>
 */
public class ProjectDetails extends BaseModel {
	private String assignedOn;
	private String releasedOn = "Not yet released";
	private String closedOn = "Not yet closed";;
	private String clientAccount;
	private String projectVertical;
	private String projectCode;
	private String projectName;
	private String projectType;

	// Numeral data project-wise
	private double yTDFte;	
	private double yTDRevenue;
	private String yTDPm;
	private double revenuePerFTE;
	private double costPerFTE;
	
	private String yTDUtilization="0 %";;
	private String utilizationB4TwoMonth="0 %";
	private String yTDAttrition;
	private String currentAttrition;
	
	private String span_1;
	private Integer spanRAGCalc;
	private String spanRagColourCode;
	private String span_2;
	
	private String projectCsatScore;
	private String projectRiskScore;
	private double rrrrPotentialFTE;
	private double rrrrPotentialRevenuePerMonth;
	private String bgvRequired;
	public String getAssignedOn() {
		return assignedOn;
	}
	public void setAssignedOn(String assignedOn) {
		this.assignedOn = assignedOn;
	}
	public String getReleasedOn() {
		return releasedOn;
	}
	public void setReleasedOn(String releasedOn) {
		this.releasedOn = releasedOn;
	}
	public String getClosedOn() {
		return closedOn;
	}
	public void setClosedOn(String closedOn) {
		this.closedOn = closedOn;
	}
	public String getClientAccount() {
		return clientAccount;
	}
	public void setClientAccount(String clientAccount) {
		this.clientAccount = clientAccount;
	}
	public String getProjectVertical() {
		return projectVertical;
	}
	public void setProjectVertical(String projectVertical) {
		this.projectVertical = projectVertical;
	}
	public String getProjectCode() {
		return projectCode;
	}
	public void setProjectCode(String projectCode) {
		this.projectCode = projectCode;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectType() {
		return projectType;
	}
	public void setProjectType(String projectType) {
		this.projectType = projectType;
	}
	public double getyTDFte() {
		return yTDFte;
	}
	public void setyTDFte(double yTDFte) {
		this.yTDFte = yTDFte;
	}
	public double getyTDRevenue() {
		return yTDRevenue;
	}
	public void setyTDRevenue(double yTDRevenue) {
		this.yTDRevenue = yTDRevenue;
	}
	public String getyTDPm() {
		return yTDPm;
	}
	public void setyTDPm(String yTDPm) {
		this.yTDPm = yTDPm;
	}
	public double getRevenuePerFTE() {
		return revenuePerFTE;
	}
	public void setRevenuePerFTE(double revenuePerFTE) {
		this.revenuePerFTE = revenuePerFTE;
	}
	public double getCostPerFTE() {
		return costPerFTE;
	}
	public void setCostPerFTE(double costPerFTE) {
		this.costPerFTE = costPerFTE;
	}
	public String getyTDUtilization() {
		return yTDUtilization;
	}
	public void setyTDUtilization(String yTDUtilization) {
		this.yTDUtilization = yTDUtilization;
	}
	public String getUtilizationB4TwoMonth() {
		return utilizationB4TwoMonth;
	}
	public void setUtilizationB4TwoMonth(String utilizationB4TwoMonth) {
		this.utilizationB4TwoMonth = utilizationB4TwoMonth;
	}
	public String getyTDAttrition() {
		return yTDAttrition;
	}
	public void setyTDAttrition(String yTDAttrition) {
		this.yTDAttrition = yTDAttrition;
	}
	public String getCurrentAttrition() {
		return currentAttrition;
	}
	public void setCurrentAttrition(String currentAttrition) {
		this.currentAttrition = currentAttrition;
	}
	public String getSpan_1() {
		return span_1;
	}
	public void setSpan_1(String span_1) {
		this.span_1 = span_1;
	}
	public Integer getSpanRAGCalc() {
		return spanRAGCalc;
	}
	public void setSpanRAGCalc(Integer spanRAGCalc) {
		this.spanRAGCalc = spanRAGCalc;
	}
	public String getSpanRagColourCode() {
		return spanRagColourCode;
	}
	public void setSpanRagColourCode(String spanRagColourCode) {
		this.spanRagColourCode = spanRagColourCode;
	}
	public String getSpan_2() {
		return span_2;
	}
	public void setSpan_2(String span_2) {
		this.span_2 = span_2;
	}
	public String getProjectCsatScore() {
		return projectCsatScore;
	}
	public void setProjectCsatScore(String projectCsatScore) {
		this.projectCsatScore = projectCsatScore;
	}
	public String getProjectRiskScore() {
		return projectRiskScore;
	}
	public void setProjectRiskScore(String projectRiskScore) {
		this.projectRiskScore = projectRiskScore;
	}
	public double getRrrrPotentialFTE() {
		return rrrrPotentialFTE;
	}
	public void setRrrrPotentialFTE(double rrrrPotentialFTE) {
		this.rrrrPotentialFTE = rrrrPotentialFTE;
	}
	public double getRrrrPotentialRevenuePerMonth() {
		return rrrrPotentialRevenuePerMonth;
	}
	public void setRrrrPotentialRevenuePerMonth(double rrrrPotentialRevenuePerMonth) {
		this.rrrrPotentialRevenuePerMonth = rrrrPotentialRevenuePerMonth;
	}
	public String getBgvRequired() {
		return bgvRequired;
	}
	public void setBgvRequired(String bgvRequired) {
		this.bgvRequired = bgvRequired;
	}
	
	
	
}
