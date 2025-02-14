package com.nus.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.sec.entities.ApexUser;
import com.nus.sec.model.ApexUserModel;
import com.nus.sec.repo.ApexUserRepository;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 10:19:27 pm<br>
 * @Objective: <br>
 */
@Service
public class ApexUserServiceImpl extends UserLoginBaseService implements ApexUserService {

	@Autowired
	ApexUserRepository apexUserRepository;
	@Autowired
	private PasswordEncoder encoder;

	@Override
	public ApexUser addApexUser(ApexUserModel apexUserModel) throws Exception{
		ApexUser savedApexUserEntity = null;
		try {
			ApexUser apexUserEntity = new ApexUser();
			apexUserEntity.setName(apexUserModel.getName());
			apexUserEntity.setEmail(apexUserModel.getEmail());
			apexUserEntity.setPwd(encoder.encode(apexUserModel.getPwd()));
			apexUserEntity.setUserContext(apexUserModel.getUserContext());
			apexUserEntity.setCreatedOn(currentDate);
			apexUserEntity.setCreatedBy(getCurrentLoginUserId());
			apexUserEntity.setActiveC(activeC);
			savedApexUserEntity = apexUserRepository.save(apexUserEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedApexUserEntity;
	}

	@Override
	public ApexUser updateApexUser(ApexUserModel apexUserModel) throws Exception{
		ApexUser apexUserEntityToBeUpdated = null;
		try {
			Optional<ApexUser> apiUserEntityWrapper = apexUserRepository.findById(apexUserModel.getId());
			if (apiUserEntityWrapper.isPresent()) {
				apexUserEntityToBeUpdated = apiUserEntityWrapper.get();
				apexUserEntityToBeUpdated.setName(apexUserModel.getName());
				apexUserEntityToBeUpdated.setEmail(apexUserModel.getEmail());
				apexUserEntityToBeUpdated.setPwd(encoder.encode(apexUserModel.getPwd()));
				apexUserEntityToBeUpdated.setUserContext(apexUserModel.getUserContext());
				apexUserEntityToBeUpdated.setModifiedOn(currentDate);
				apexUserEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				apexUserRepository.save(apexUserEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return apexUserEntityToBeUpdated;

	}

	@Override
	public boolean deleteApexUserByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			apexUserRepository.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;

	}

	@Override
	public ApexUser getApexUserByRecordId(Integer recordId)throws Exception {
		ApexUser apexUserEntityFetched = null;
		try {
			Optional<ApexUser> apexUserEntityWrapper = apexUserRepository.findById(recordId);
			if (apexUserEntityWrapper.isPresent())
				apexUserEntityFetched = apexUserEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return apexUserEntityFetched;
	}

	@Override
	public List<ApexUser> getAllApexUsers() throws Exception{
		List<ApexUser> apexUserList = null;
		try {
			apexUserList = apexUserRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
		return apexUserList;

	}

	@Override
	public Integer findByEmail(String email) throws Exception{
		int userId = 0;
		try {
			userId = apexUserRepository.findByEmail(email).get().getId();
		}catch(Exception ex) {
			throw ex;
		}
		return userId;
	}

}
