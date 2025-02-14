/*
 * Created on 2025-01-24 ( 01:39:33 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.nus.sec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/**
 * JPA entity class for "UserType"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="user_grade", schema="public" )
public class UserGrade implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="user_grade", nullable=false, length=100)
    private String     userGrade ;
    @JsonIgnore
    @Column(name="created_by")
    private Integer    createdBy ;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date       createdOn ;

    @Column(name="modified_by")
    private Integer    modifiedBy ;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_on")
    private Date       modifiedOn ;

    @Column(name="active_c", nullable=false, length=1)
    private String     activeC ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser ; 

    @ManyToOne
    @JoinColumn(name="modified_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser2 ; 

    @OneToMany(mappedBy="userGrade")
    private List<ApexUser> listOfApexUser ; 


    /**
     * Constructor
     */
    public UserGrade() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setUserGrade( String userGrade ) {
        this.userGrade = userGrade ;
    }
    public String getUserGrade() {
        return this.userGrade;
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

    //--- GETTERS FOR LINKS
    public ApexUser getApexUser() {
        return this.apexUser;
    } 

    public ApexUser getApexUser2() {
        return this.apexUser2;
    } 

    public List<ApexUser> getListOfApexUser() {
        return this.listOfApexUser;
    } 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(userGrade);
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
        return sb.toString(); 
    } 

}
