package com.nus.fileupload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.fileupload.entities.FileReference;
import com.nus.fileupload.repo.FileReferenceRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:30:06 pm<br>
 * @Objective: <br>
 */
@Service
public class FileReferenceServiceImpl extends UserLoginBaseService implements FileReferenceService{
	
	@Autowired
	FileReferenceRepo fileReferenceRepo;

	@Override
	public boolean deleteFileReferenceByRecordId(Integer recordId)throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public FileReference getFileReferenceByRecordId(Integer recordId) throws Exception{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<FileReference> getAllFileReference()throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
