package com.nus.pvt.reports.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Feb-2025<br>
 * @Time: 12:47:03 am<br>
 * @Objective: <br>
 */
public class PmProjectReportModel {
	private String pm;
	
	private String empGrade;	
	private String fMonthYr;
	private String tMonthYr;
	private String reportFor;
	private List<ProjectDetails> projectDetailReportList = new ArrayList<ProjectDetails>();
	/**
	 * @return the pm
	 */
	public String getPm() {
		return pm;
	}
	/**
	 * @param pm the pm to set
	 */
	public void setPm(String pm) {
		this.pm = pm;
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
	/**
	 * @return the fMonthYr
	 */
	public String getfMonthYr() {
		return fMonthYr;
	}
	/**
	 * @param fMonthYr the fMonthYr to set
	 */
	public void setfMonthYr(String fMonthYr) {
		this.fMonthYr = fMonthYr;
	}
	/**
	 * @return the tMonthYr
	 */
	public String gettMonthYr() {
		return tMonthYr;
	}
	/**
	 * @param tMonthYr the tMonthYr to set
	 */
	public void settMonthYr(String tMonthYr) {
		this.tMonthYr = tMonthYr;
	}
	/**
	 * @return the reportFor
	 */
	public String getReportFor() {
		return reportFor;
	}
	/**
	 * @param reportFor the reportFor to set
	 */
	public void setReportFor(String reportFor) {
		this.reportFor = reportFor;
	}
	/**
	 * @return the projectDetailReportList
	 */
	public List<ProjectDetails> getProjectDetailReportList() {
		return projectDetailReportList;
	}
	/**
	 * @param projectDetailReportList the projectDetailReportList to set
	 */
	public void setProjectDetailReportList(List<ProjectDetails> projectDetailReportList) {
		this.projectDetailReportList = projectDetailReportList;
	}
	
	
}
