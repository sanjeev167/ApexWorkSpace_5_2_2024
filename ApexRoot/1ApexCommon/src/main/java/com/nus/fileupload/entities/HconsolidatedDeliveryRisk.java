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
 * JPA entity class for "HconsolidatedDeliveryRisk"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="hconsolidated_delivery_risk", schema="public" )
public class HconsolidatedDeliveryRisk implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="risk_type", nullable=false, length=50)
    private String     riskType ;

    @Column(name="source", length=50)
    private String     source ;

    @Column(name="risk_main_category", length=2147483647)
    private String     riskMainCategory ;

    @Column(name="risk_sub_category", length=2147483647)
    private String     riskSubCategory ;

    @Column(name="occuring_probablity", length=50)
    private String     occuringProbablity ;

    @Column(name="impact", length=50)
    private String     impact ;

    @Column(name="risk_priority_number")
    private Double     riskPriorityNumber ;

    @Column(name="risk_exposure")
    private Double     riskExposure ;

    @Column(name="response_resolution_startegy", length=50)
    private String     responseResolutionStartegy ;

    @Column(name="risk_owner", length=50)
    private String     riskOwner ;

    @Column(name="mitigation_plan", length=2000)
    private String     mitigationPlan ;

    @Column(name="contigency_plan", length=2000)
    private String     contigencyPlan ;

    @Column(name="risk_status", length=25)
    private String     riskStatus ;

    @Column(name="remark", length=2000)
    private String     remark ;

    @Column(name="project_code_id")
    private Integer    projectCodeId ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.DATE)
    @Column(name="file_upload_date")
    private Date    fileUploadDate ;

    @Column(name="created_by")
    private Integer    createdBy ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date       createdOn ;

    @Column(name="modified_by")
    private Integer    modifiedBy ;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_on")
    private Date       modifiedOn ;

    @Column(name="active_c", length=1)
    private String     activeC ;

    @Column(name="file_reference_id")
    private Integer    fileReferenceId ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="modified_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser2 ; 

    @ManyToOne
    @JoinColumn(name="file_reference_id", referencedColumnName="id", insertable=false, updatable=false)
    private FileReference fileReference ; 

    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser ; 


    /**
     * Constructor
     */
    public HconsolidatedDeliveryRisk() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setRiskType( String riskType ) {
        this.riskType = riskType ;
    }
    public String getRiskType() {
        return this.riskType;
    }

    public void setSource( String source ) {
        this.source = source ;
    }
    public String getSource() {
        return this.source;
    }

    public void setRiskMainCategory( String riskMainCategory ) {
        this.riskMainCategory = riskMainCategory ;
    }
    public String getRiskMainCategory() {
        return this.riskMainCategory;
    }

    public void setRiskSubCategory( String riskSubCategory ) {
        this.riskSubCategory = riskSubCategory ;
    }
    public String getRiskSubCategory() {
        return this.riskSubCategory;
    }

    public void setOccuringProbablity( String occuringProbablity ) {
        this.occuringProbablity = occuringProbablity ;
    }
    public String getOccuringProbablity() {
        return this.occuringProbablity;
    }

    public void setImpact( String impact ) {
        this.impact = impact ;
    }
    public String getImpact() {
        return this.impact;
    }

    public void setRiskPriorityNumber( Double riskPriorityNumber ) {
        this.riskPriorityNumber = riskPriorityNumber ;
    }
    public Double getRiskPriorityNumber() {
        return this.riskPriorityNumber;
    }

    public void setRiskExposure( Double riskExposure ) {
        this.riskExposure = riskExposure ;
    }
    public Double getRiskExposure() {
        return this.riskExposure;
    }

    public void setResponseResolutionStartegy( String responseResolutionStartegy ) {
        this.responseResolutionStartegy = responseResolutionStartegy ;
    }
    public String getResponseResolutionStartegy() {
        return this.responseResolutionStartegy;
    }

    public void setRiskOwner( String riskOwner ) {
        this.riskOwner = riskOwner ;
    }
    public String getRiskOwner() {
        return this.riskOwner;
    }

    public void setMitigationPlan( String mitigationPlan ) {
        this.mitigationPlan = mitigationPlan ;
    }
    public String getMitigationPlan() {
        return this.mitigationPlan;
    }

    public void setContigencyPlan( String contigencyPlan ) {
        this.contigencyPlan = contigencyPlan ;
    }
    public String getContigencyPlan() {
        return this.contigencyPlan;
    }

    public void setRiskStatus( String riskStatus ) {
        this.riskStatus = riskStatus ;
    }
    public String getRiskStatus() {
        return this.riskStatus;
    }

    public void setRemark( String remark ) {
        this.remark = remark ;
    }
    public String getRemark() {
        return this.remark;
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

	public void setCreatedBy( Integer createdBy ) {
        this.createdBy = createdBy ;
    }
    public Integer getCreatedBy() {
        return this.createdBy;
    }

    public void setCreatedOn( Date createdOn ) {
        this.createdOn = createdOn ;
    }
    public Date getCreatedOn() {
        return this.createdOn;
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
    public ApexUser getApexUser2() {
        return this.apexUser2;
    } 

    public FileReference getFileReference() {
        return this.fileReference;
    } 

    public ApexUser getApexUser() {
        return this.apexUser;
    } 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(riskType);
        sb.append("|");
        sb.append(source);
        sb.append("|");
        sb.append(riskMainCategory);
        sb.append("|");
        sb.append(riskSubCategory);
        sb.append("|");
        sb.append(occuringProbablity);
        sb.append("|");
        sb.append(impact);
        sb.append("|");
        sb.append(riskPriorityNumber);
        sb.append("|");
        sb.append(riskExposure);
        sb.append("|");
        sb.append(responseResolutionStartegy);
        sb.append("|");
        sb.append(riskOwner);
        sb.append("|");
        sb.append(mitigationPlan);
        sb.append("|");
        sb.append(contigencyPlan);
        sb.append("|");
        sb.append(riskStatus);
        sb.append("|");
        sb.append(remark);
        sb.append("|");
        sb.append(projectCodeId);
        sb.append("|");
        sb.append(fileUploadDate);
        
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(createdOn);
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
