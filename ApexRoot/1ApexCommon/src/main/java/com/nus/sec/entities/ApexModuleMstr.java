/*
 * Created on 2025-01-24 ( 01:39:32 )
 * Generated by Telosys ( http://www.telosys.org/ ) version 3.3.0
 */
package com.nus.sec.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

/**
 * JPA entity class for "ApexModuleMstr"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="apex_module_mstr", schema="public" )
public class ApexModuleMstr implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="name", nullable=false, length=50)
    private String     name ;

    @JsonIgnore
    @Column(name="created_by")
    private Integer    createdBy ;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="created_on")
    private Date       createdOn ;

    @JsonIgnore
    @Column(name="modified_by")
    private Integer    modifiedBy ;

    @JsonIgnore
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="modified_on")
    private Date       modifiedOn ;

    @Column(name="active_c", nullable=false, length=1)
    private String     activeC ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="apexModuleMstr")
    private List<PageMstr> listOfPageMstr ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="modified_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser2 ; 


    /**
     * Constructor
     */
    public ApexModuleMstr() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setName( String name ) {
        this.name = name ;
    }
    public String getName() {
        return this.name;
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
    public List<PageMstr> getListOfPageMstr() {
        return this.listOfPageMstr;
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
        sb.append(name);
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
