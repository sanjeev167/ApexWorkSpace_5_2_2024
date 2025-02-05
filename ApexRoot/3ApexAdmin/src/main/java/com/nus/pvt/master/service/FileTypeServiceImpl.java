package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.fileupload.entities.FileType;
import com.nus.pvt.master.model.FileTypeModel;
import com.nus.pvt.master.repo.FileTypeRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:32:42 pm<br>
 * @Objective: <br>
 */
@Service
public class FileTypeServiceImpl extends UserLoginBaseService implements FileTypeService{

	@Autowired
	 FileTypeRepo fileTypeRepo;
	
	@Override
	public FileType addFileType(FileTypeModel fileTypeModel) throws Exception{
		FileType savedFileTypeEntity = null;
		try {
			FileType fileTypeEntity = new FileType();
			fileTypeEntity.setFileType(fileTypeModel.getFileType());
			fileTypeEntity.setCreatedOn(currentDate);
			fileTypeEntity.setCreatedBy(getCurrentLoginUserId());
			fileTypeEntity.setActiveC("Y");
			savedFileTypeEntity = fileTypeRepo.save(fileTypeEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedFileTypeEntity;
	}

	@Override
	public FileType updateFileType(FileTypeModel fileTypeModel) throws Exception{
		FileType fileTypeEntityToBeUpdated = null;
		try {
			Optional<FileType> fileTypeEntityWrapper = fileTypeRepo.findById(fileTypeModel.getId());
			if (fileTypeEntityWrapper.isPresent()) {
				fileTypeEntityToBeUpdated = fileTypeEntityWrapper.get();
				fileTypeEntityToBeUpdated.setFileType(fileTypeModel.getFileType());								
				fileTypeEntityToBeUpdated.setModifiedOn(currentDate);
				fileTypeEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				fileTypeRepo.save(fileTypeEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return fileTypeEntityToBeUpdated;
	}

	@Override
	public boolean deleteFileTypeByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			fileTypeRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public FileType getFileTypeByRecordId(Integer recordId) throws Exception{
		FileType fileTypeEntityFetched = null;
		try {
			Optional<FileType> fileTypeEntityWrapper = fileTypeRepo.findById(recordId);
			if (fileTypeEntityWrapper.isPresent())
				fileTypeEntityFetched = fileTypeEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return fileTypeEntityFetched;
	}

	@Override
	public List<FileType> getAllFileTypes() throws Exception{
		List<FileType> fileTypeList = null;
		try {
			fileTypeList = fileTypeRepo.findAllByActiveC("Y");
		} catch (Exception ex) {
			throw ex;
		}
		return fileTypeList;
	}
}
