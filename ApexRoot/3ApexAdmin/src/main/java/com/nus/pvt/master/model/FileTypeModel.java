package com.nus.pvt.master.model;

import com.nus.base.model.BaseModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 03-Feb-2025<br>
 * @Time: 10:19:09 pm<br>
 * @Objective: <br>
 */
public class FileTypeModel extends BaseModel{

	 private String fileType ;

	public FileTypeModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the fileType
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * @param fileType the fileType to set
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	 
	 
}
