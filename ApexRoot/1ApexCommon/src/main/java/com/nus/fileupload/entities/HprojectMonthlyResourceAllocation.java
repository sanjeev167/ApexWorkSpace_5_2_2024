/*
 * Created on 2025-01-30 ( 11:46:16 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.nus.fileupload.entities;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.nus.sec.entities.ApexUser;

/**
 * JPA entity class for "HprojectMonthlyResourceAllocation"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="hproject_monthly_resource_allocation", schema="public" )
public class HprojectMonthlyResourceAllocation implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="emp_status", nullable=false, length=50)
    private String     empStatus ;

    @Column(name="allocation_percentage", nullable=false)
    private Double     allocationPercentage ;

    @Column(name="billing_status", nullable=false, length=50)
    private String     billingStatus ;

    @Column(name="emp_grade", nullable=false, length=50)
    private String     empGrade ;

    @Column(name="project_code_id", nullable=false)
    private Integer    projectCodeId ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name="file_upload_date")
    private Date    fileUploadDate ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date       createdOn ;

    @Column(name="created_by")
    private Integer    createdBy ;

    @Column(name="modified_by")
    private Integer    modifiedBy ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_on")
    private Date       modifiedOn ;

    @Column(name="active_c", nullable=false, length=1)
    private String     activeC ;

    @Column(name="file_reference_id")
    private Integer    fileReferenceId ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="file_reference_id", referencedColumnName="id", insertable=false, updatable=false)
    private FileReference fileReference ; 

    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser ; 

    @ManyToOne
    @JoinColumn(name="modified_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser2 ; 


    /**
     * Constructor
     */
    public HprojectMonthlyResourceAllocation() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setEmpStatus( String empStatus ) {
        this.empStatus = empStatus ;
    }
    public String getEmpStatus() {
        return this.empStatus;
    }

    public void setAllocationPercentage( Double allocationPercentage ) {
        this.allocationPercentage = allocationPercentage ;
    }
    public Double getAllocationPercentage() {
        return this.allocationPercentage;
    }

    public void setBillingStatus( String billingStatus ) {
        this.billingStatus = billingStatus ;
    }
    public String getBillingStatus() {
        return this.billingStatus;
    }

    public void setEmpGrade( String empGrade ) {
        this.empGrade = empGrade ;
    }
    public String getEmpGrade() {
        return this.empGrade;
    }

    public void setProjectCodeId( Integer projectCodeId ) {
        this.projectCodeId = projectCodeId ;
    }
    public Integer getProjectCodeId() {
        return this.projectCodeId;
    }

    

    public Date getFileUploadDate() {
		return fileUploadDate;
	}

	public void setFileUploadDate(Date fileUploadDate) {
		this.fileUploadDate = fileUploadDate;
	}

	public void setCreatedOn( Date createdOn ) {
        this.createdOn = createdOn ;
    }
    public Date getCreatedOn() {
        return this.createdOn;
    }

    public void setCreatedBy( Integer createdBy ) {
        this.createdBy = createdBy ;
    }
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setModifiedBy( Integer modifiedBy ) {
        this.modifiedBy = modifiedBy ;
    }
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setModifiedOn( Date modifiedOn ) {
        this.modifiedOn = modifiedOn ;
    }
    public Date getModifiedOn() {
        return this.modifiedOn;
    }

    public void setActiveC( String activeC ) {
        this.activeC = activeC ;
    }
    public String getActiveC() {
        return this.activeC;
    }

    public void setFileReferenceId( Integer fileReferenceId ) {
        this.fileReferenceId = fileReferenceId ;
    }
    public Integer getFileReferenceId() {
        return this.fileReferenceId;
    }

    //--- GETTERS FOR LINKS
    public FileReference getFileReference() {
        return this.fileReference;
    } 

    public ApexUser getApexUser() {
        return this.apexUser;
    } 

    public ApexUser getApexUser2() {
        return this.apexUser2;
    } 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(empStatus);
        sb.append("|");
        sb.append(allocationPercentage);
        sb.append("|");
        sb.append(billingStatus);
        sb.append("|");
        sb.append(empGrade);
        sb.append("|");
        sb.append(projectCodeId);
        sb.append("|");
        sb.append(fileUploadDate);
        
        sb.append("|");
        sb.append(createdOn);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(modifiedBy);
        sb.append("|");
        sb.append(modifiedOn);
        sb.append("|");
        sb.append(activeC);
        sb.append("|");
        sb.append(fileReferenceId);
        return sb.toString(); 
    } 

}
