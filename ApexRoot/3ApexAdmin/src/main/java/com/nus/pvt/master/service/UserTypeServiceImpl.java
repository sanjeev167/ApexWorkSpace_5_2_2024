package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.master.model.UserTypeModel;
import com.nus.pvt.master.repo.UserTypeRepo;
import com.nus.sec.entities.UserType;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:29:17 pm<br>
 * @Objective: <br>
 */
@Service
public class UserTypeServiceImpl extends UserLoginBaseService implements UserTypeService {

	@Autowired
	UserTypeRepo userTypeRepo;	

	@Override
	public UserType addUserType(UserTypeModel userTypeModel) throws Exception{
		UserType savedUserTypeEntity = null;
		try {
			UserType userTypeEntity = new UserType();
			userTypeEntity.setUserType(userTypeModel.getUserType());
			userTypeEntity.setCreatedOn(currentDate);
			userTypeEntity.setCreatedBy(getCurrentLoginUserId());
			userTypeEntity.setActiveC("Y");
			savedUserTypeEntity = userTypeRepo.save(userTypeEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedUserTypeEntity;
	}

	@Override
	public UserType updateUserType(UserTypeModel userTypeModel) throws Exception{
		UserType userTypeEntityToBeUpdated = null;
		try {
			Optional<UserType> userTypeEntityWrapper = userTypeRepo.findById(userTypeModel.getId());
			if (userTypeEntityWrapper.isPresent()) {
				userTypeEntityToBeUpdated = userTypeEntityWrapper.get();
				userTypeEntityToBeUpdated.setUserType(userTypeModel.getUserType());								
				userTypeEntityToBeUpdated.setModifiedOn(currentDate);
				userTypeEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				userTypeRepo.save(userTypeEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return userTypeEntityToBeUpdated;
	}

	@Override
	public boolean deleteUserTypeByRecordId(Integer recordId)throws Exception {
		boolean isRecordDeleted = true;
		try {
			userTypeRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public UserType getUserTypeByRecordId(Integer recordId) throws Exception{
		UserType userTypeEntityFetched = null;
		try {
			Optional<UserType> userTypeEntityWrapper = userTypeRepo.findById(recordId);
			if (userTypeEntityWrapper.isPresent())
				userTypeEntityFetched = userTypeEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return userTypeEntityFetched;
	}

	@Override
	public List<UserType> getAllUserTypes() throws Exception{
		List<UserType> userTypeList = null;
		try {
			userTypeList = userTypeRepo.findAllByActiveC("Y");
		} catch (Exception ex) {
			throw ex;
		}
		return userTypeList;
	}
	
	
}
