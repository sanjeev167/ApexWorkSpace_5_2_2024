/*
 * Created on 2025-01-24 ( 01:39:32 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.nus.sec.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * JPA entity class for "ApexGroupRole"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="apex_group_role", schema="public" )
public class ApexGroupRole implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="apex_group_id", nullable=false)
    private Integer    apexGroupId ;

    @Column(name="apex_role_id", nullable=false)
    private Integer    apexRoleId ;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date       createdOn ;

    @JsonIgnore
    @Column(name="created_by")
    private Integer    createdBy ;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_on")
    private Date       modifiedOn ;

    @JsonIgnore
    @Column(name="modified_by")
    private Integer    modifiedBy ;

    @Column(name="active_c", nullable=false, length=1)
    private String     activeC ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="apex_role_id", referencedColumnName="id", insertable=false, updatable=false)
    private ApexRole   apexRole ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="apex_group_id", referencedColumnName="id", insertable=false, updatable=false)
    private ApexGroup  apexGroup ; 


    /**
     * Constructor
     */
    public ApexGroupRole() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setApexGroupId( Integer apexGroupId ) {
        this.apexGroupId = apexGroupId ;
    }
    public Integer getApexGroupId() {
        return this.apexGroupId;
    }

    public void setApexRoleId( Integer apexRoleId ) {
        this.apexRoleId = apexRoleId ;
    }
    public Integer getApexRoleId() {
        return this.apexRoleId;
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

    public void setModifiedOn( Date modifiedOn ) {
        this.modifiedOn = modifiedOn ;
    }
    public Date getModifiedOn() {
        return this.modifiedOn;
    }

    public void setModifiedBy( Integer modifiedBy ) {
        this.modifiedBy = modifiedBy ;
    }
    public Integer getModifiedBy() {
        return this.modifiedBy;
    }

    public void setActiveC( String activeC ) {
        this.activeC = activeC ;
    }
    public String getActiveC() {
        return this.activeC;
    }

    //--- GETTERS FOR LINKS
    public ApexRole getApexRole() {
        return this.apexRole;
    } 

    public ApexGroup getApexGroup() {
        return this.apexGroup;
    } 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(apexGroupId);
        sb.append("|");
        sb.append(apexRoleId);
        sb.append("|");
        sb.append(createdOn);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(modifiedOn);
        sb.append("|");
        sb.append(modifiedBy);
        sb.append("|");
        sb.append(activeC);
        return sb.toString(); 
    } 

}
