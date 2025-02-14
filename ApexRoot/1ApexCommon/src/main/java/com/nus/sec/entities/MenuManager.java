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
 * JPA entity class for "MenuManager"
 *
 * @author Telosys
 *
 */
@Entity
@Table(name="menu_manager", schema="public" )
public class MenuManager implements Serializable {

    private static final long serialVersionUID = 1L;

    //--- ENTITY PRIMARY KEY 
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Integer    id ;

    //--- ENTITY DATA FIELDS 
    @Column(name="parent_node_id")
    private Integer    parentNodeId ;

    @Column(name="node_name", length=50)
    private String     nodeName ;

    @Column(name="node_type", length=1)
    private String     nodeType ;

    @Column(name="tree_menu_type_id")
    private Integer    treeMenuTypeId ;

    @Column(name="img_url", length=50)
    private String     imgUrl ;

    @Column(name="page_id")
    private Integer    pageId ;

    @JsonIgnore
    @Column(name="created_by")
    private Integer    createdBy ;

    @JsonIgnore
    @Temporal(TemporalType.TIME)
    @Column(name="created_on")
    private Date       createdOn ;

    @JsonIgnore
    @Column(name="updated_by")
    private Integer    updatedBy ;

    @JsonIgnore
    @Temporal(TemporalType.TIME)
    @Column(name="updated_on")
    private Date       updatedOn ;

    @Column(name="active_c", nullable=false, length=1)
    private String     activeC ;


    //--- ENTITY LINKS ( RELATIONSHIP )
    @JsonIgnore
    @OneToMany(mappedBy="menuManager")
    private List<MenuManager> listOfMenuManager ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="updated_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser2 ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="parent_node_id", referencedColumnName="id", insertable=false, updatable=false)
    private MenuManager menuManager ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="page_id", referencedColumnName="id", insertable=false, updatable=false)
    private PageMstr   pageMstr ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="created_by", referencedColumnName="id", insertable=false, updatable=false)
    private ApexUser   apexUser ; 

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="tree_menu_type_id", referencedColumnName="id", insertable=false, updatable=false)
    private TreeMenuTypeMstr treeMenuTypeMstr ; 


    /**
     * Constructor
     */
    public MenuManager() {
		super();
    }
    
    //--- GETTERS & SETTERS FOR FIELDS
    public void setId( Integer id ) {
        this.id = id ;
    }
    public Integer getId() {
        return this.id;
    }

    public void setParentNodeId( Integer parentNodeId ) {
        this.parentNodeId = parentNodeId ;
    }
    public Integer getParentNodeId() {
        return this.parentNodeId;
    }

    public void setNodeName( String nodeName ) {
        this.nodeName = nodeName ;
    }
    public String getNodeName() {
        return this.nodeName;
    }

    public void setNodeType( String nodeType ) {
        this.nodeType = nodeType ;
    }
    public String getNodeType() {
        return this.nodeType;
    }

    public void setTreeMenuTypeId( Integer treeMenuTypeId ) {
        this.treeMenuTypeId = treeMenuTypeId ;
    }
    public Integer getTreeMenuTypeId() {
        return this.treeMenuTypeId;
    }

    public void setImgUrl( String imgUrl ) {
        this.imgUrl = imgUrl ;
    }
    public String getImgUrl() {
        return this.imgUrl;
    }

    public void setPageId( Integer pageId ) {
        this.pageId = pageId ;
    }
    public Integer getPageId() {
        return this.pageId;
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

    public void setUpdatedBy( Integer updatedBy ) {
        this.updatedBy = updatedBy ;
    }
    public Integer getUpdatedBy() {
        return this.updatedBy;
    }

    public void setUpdatedOn( Date updatedOn ) {
        this.updatedOn = updatedOn ;
    }
    public Date getUpdatedOn() {
        return this.updatedOn;
    }

    public void setActiveC( String activeC ) {
        this.activeC = activeC ;
    }
    public String getActiveC() {
        return this.activeC;
    }

    //--- GETTERS FOR LINKS
    public List<MenuManager> getListOfMenuManager() {
        return this.listOfMenuManager;
    } 

    public ApexUser getApexUser2() {
        return this.apexUser2;
    } 

    public MenuManager getMenuManager() {
        return this.menuManager;
    } 

    public PageMstr getPageMstr() {
        return this.pageMstr;
    } 

    public ApexUser getApexUser() {
        return this.apexUser;
    } 

    public TreeMenuTypeMstr getTreeMenuTypeMstr() {
        return this.treeMenuTypeMstr;
    } 

    //--- toString specific method
	@Override
    public String toString() { 
        StringBuilder sb = new StringBuilder(); 
        sb.append(id);
        sb.append("|");
        sb.append(parentNodeId);
        sb.append("|");
        sb.append(nodeName);
        sb.append("|");
        sb.append(nodeType);
        sb.append("|");
        sb.append(treeMenuTypeId);
        sb.append("|");
        sb.append(imgUrl);
        sb.append("|");
        sb.append(pageId);
        sb.append("|");
        sb.append(createdBy);
        sb.append("|");
        sb.append(createdOn);
        sb.append("|");
        sb.append(updatedBy);
        sb.append("|");
        sb.append(updatedOn);
        sb.append("|");
        sb.append(activeC);
        return sb.toString(); 
    } 

}
