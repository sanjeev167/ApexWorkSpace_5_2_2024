package com.nus.fileupload.model;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 14-Feb-2025<br>
 * @Time: 10:22:25 pm<br>
 * @Objective: <br>
 */
public class ResourceAllocationModel {
	
	
	
	public ResourceAllocationModel(String emp_code, double allocation_percentage) {
		super();
		this.emp_code = emp_code;
		this.allocation_percentage = allocation_percentage;
	}
	public ResourceAllocationModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	private String emp_code;
	private double allocation_percentage;
	public String getEmp_code() {
		return emp_code;
	}
	public void setEmp_code(String emp_code) {
		this.emp_code = emp_code;
	}
	public double getAllocation_percentage() {
		return allocation_percentage;
	}
	public void setAllocation_percentage(double allocation_percentage) {
		this.allocation_percentage = allocation_percentage;
	}
	

}
