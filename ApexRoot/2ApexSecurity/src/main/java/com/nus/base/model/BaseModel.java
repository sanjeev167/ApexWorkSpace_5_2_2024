package com.nus.base.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:34:50 pm<br>
 * @Objective: <br>
 */
public class BaseModel {

	 @JsonIgnore
	private Integer id;

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	
}
