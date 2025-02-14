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
	private String yTDPmPercent;
	private double revenuePerFTE;
	private double costPerFTE;
	
	private String yTDUtilPercent;
	private String twoMonthBackMonthUtilPercent;
	private String yTDAttritionPercent;
	private String currentAttritionPercent;
	
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
	public String getyTDPmPercent() {
		return yTDPmPercent;
	}
	public void setyTDPmPercent(String yTDPmPercent) {
		this.yTDPmPercent = yTDPmPercent;
	}
	public String getyTDUtilPercent() {
		return yTDUtilPercent;
	}
	public void setyTDUtilPercent(String yTDUtilPercent) {
		this.yTDUtilPercent = yTDUtilPercent;
	}
	public String getTwoMonthBackMonthUtilPercent() {
		return twoMonthBackMonthUtilPercent;
	}
	public void setTwoMonthBackMonthUtilPercent(String twoMonthBackMonthUtilPercent) {
		this.twoMonthBackMonthUtilPercent = twoMonthBackMonthUtilPercent;
	}
	public String getyTDAttritionPercent() {
		return yTDAttritionPercent;
	}
	public void setyTDAttritionPercent(String yTDAttritionPercent) {
		this.yTDAttritionPercent = yTDAttritionPercent;
	}
	public String getCurrentAttritionPercent() {
		return currentAttritionPercent;
	}
	public void setCurrentAttritionPercent(String currentAttritionPercent) {
		this.currentAttritionPercent = currentAttritionPercent;
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
