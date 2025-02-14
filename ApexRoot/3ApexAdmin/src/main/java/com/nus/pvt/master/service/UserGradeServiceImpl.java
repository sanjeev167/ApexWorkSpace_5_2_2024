package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.master.model.UserGradeModel;
import com.nus.pvt.master.repo.UserGradeRepo;
import com.nus.sec.entities.UserGrade;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:29:17 pm<br>
 * @Objective: <br>
 */
@Service
public class UserGradeServiceImpl extends UserLoginBaseService implements UserGradeService {

	@Autowired
	UserGradeRepo userGradeRepo;	

	@Override
	public UserGrade addUserGrade(UserGradeModel userGradeModel) throws Exception{
		UserGrade savedUserGradeEntity = null;
		try {
			UserGrade userGradeEntity = new UserGrade();
			userGradeEntity.setUserGrade(userGradeModel.getUserGrade());
			userGradeEntity.setCreatedOn(currentDate);
			userGradeEntity.setCreatedBy(getCurrentLoginUserId());
			userGradeEntity.setActiveC(activeC);
			savedUserGradeEntity = userGradeRepo.save(userGradeEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedUserGradeEntity;
	}

	@Override
	public UserGrade updateUserGrade(UserGradeModel userGradeModel) throws Exception{
		UserGrade userGradeEntityToBeUpdated = null;
		try {
			Optional<UserGrade> userGradeEntityWrapper = userGradeRepo.findById(userGradeModel.getId());
			if (userGradeEntityWrapper.isPresent()) {
				userGradeEntityToBeUpdated = userGradeEntityWrapper.get();
				userGradeEntityToBeUpdated.setUserGrade(userGradeModel.getUserGrade());								
				userGradeEntityToBeUpdated.setModifiedOn(currentDate);
				userGradeEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				userGradeRepo.save(userGradeEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return userGradeEntityToBeUpdated;
	}

	@Override
	public boolean deleteUserGradeByRecordId(Integer recordId)throws Exception {
		boolean isRecordDeleted = true;
		try {
			userGradeRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public UserGrade getUserGradeByRecordId(Integer recordId) throws Exception{
		UserGrade userGradeEntityFetched = null;
		try {
			Optional<UserGrade> userGradeEntityWrapper = userGradeRepo.findById(recordId);
			if (userGradeEntityWrapper.isPresent())
				userGradeEntityFetched = userGradeEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return userGradeEntityFetched;
	}

	@Override
	public List<UserGrade> getAllUserGrades() throws Exception{
		List<UserGrade> userGradeList = null;
		try {
			userGradeList = userGradeRepo.findAllByActiveC(activeC);
		} catch (Exception ex) {
			throw ex;
		}
		return userGradeList;
	}	
}
