package com.nus.pvt.master.service;

import java.util.List;

import com.nus.fileupload.entities.FileType;
import com.nus.pvt.master.model.FileTypeModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:32:14 pm<br>
 * @Objective: <br>
 */
public interface FileTypeService {	
   
	public FileType addFileType(FileTypeModel fileTypeModel)throws Exception;
	public FileType updateFileType(FileTypeModel fileTypeModel)throws Exception;
	public boolean deleteFileTypeByRecordId(Integer recordId)throws Exception;
	public FileType getFileTypeByRecordId(Integer recordId)throws Exception;
	public List<FileType> getAllFileTypes()throws Exception;

}
