package com.nus.pvt.admin.entities;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Embeddable;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 10-Feb-2025<br>
 * @Time: 1:21:11 am<br>
 * @Objective: <br>
 */
@Embeddable
public class AssignProjectToPmCompositeKey implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Integer    projectCodeId ;	
	private Integer    pmUserId ;	
	
	public AssignProjectToPmCompositeKey() {
		super();		
	}
	
	public AssignProjectToPmCompositeKey(Integer projectCodeId, Integer pmUserId) {		
		this.projectCodeId = projectCodeId;
		this.pmUserId = pmUserId;
	}

	/**
	 * @return the projectCodeId
	 */
	public Integer getProjectCodeId() {
		return projectCodeId;
	}

	/**
	 * @param projectCodeId the projectCodeId to set
	 */
	public void setProjectCodeId(Integer projectCodeId) {
		this.projectCodeId = projectCodeId;
	}

	/**
	 * @return the pmUserId
	 */
	public Integer getPmUserId() {
		return pmUserId;
	}

	/**
	 * @param pmUserId the pmUserId to set
	 */
	public void setPmUserId(Integer pmUserId) {
		this.pmUserId = pmUserId;
	}		
	 
	 @Override
     public boolean equals(Object o) {
         if (this == o) return true;
         if (o == null || getClass() != o.getClass()) return false;
         AssignProjectToPmCompositeKey assignProjectToPmId = (AssignProjectToPmCompositeKey) o;
         return Objects.equals(projectCodeId, assignProjectToPmId.getProjectCodeId()) && Objects.equals(pmUserId, assignProjectToPmId.getPmUserId());
     }

     @Override
     public int hashCode() {
         return Objects.hash(projectCodeId, pmUserId);
     }	
}
