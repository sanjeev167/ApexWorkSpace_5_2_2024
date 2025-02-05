package com.nus.fileupload.service;

import java.util.List;

import com.nus.fileupload.entities.FileReference;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:29:45 pm<br>
 * @Objective: <br>
 */
public interface FileReferenceService {	

	public boolean deleteFileReferenceByRecordId(Integer recordId)throws Exception;

	public FileReference getFileReferenceByRecordId(Integer recordId)throws Exception;

	public List<FileReference> getAllFileReference()throws Exception;
}
