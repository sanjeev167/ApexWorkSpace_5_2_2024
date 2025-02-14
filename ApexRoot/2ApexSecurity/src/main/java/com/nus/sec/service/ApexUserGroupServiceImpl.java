package com.nus.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.sec.entities.ApexUserGroup;
import com.nus.sec.model.ApexUserGroupModel;
import com.nus.sec.repo.ApexUserGroupRepository;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 10:06:43 pm<br>
 * @Objective: <br>
 */
@Service
public class ApexUserGroupServiceImpl extends UserLoginBaseService implements ApexUserGroupService {

	@Autowired
	ApexUserGroupRepository apexUserGroupRepository;

	@Override
	public ApexUserGroup addApexUserGroup(ApexUserGroupModel apexUserGroupModel)throws Exception {
		ApexUserGroup savedApexUserGroupEntity = null;
		try {
			ApexUserGroup apexUserGroupEntity = new ApexUserGroup();
			apexUserGroupEntity.setApexUserId(apexUserGroupModel.getApexUserId());
			apexUserGroupEntity.setApexGroupId(apexUserGroupModel.getApexGroupId());
			apexUserGroupEntity.setCreatedOn(currentDate);
			apexUserGroupEntity.setCreatedBy(getCurrentLoginUserId());			
			apexUserGroupEntity.setActiveC(activeC);
			savedApexUserGroupEntity = apexUserGroupRepository.save(apexUserGroupEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedApexUserGroupEntity;

	}

	@Override
	public ApexUserGroup updateApexUserGroup(ApexUserGroupModel apexUserGroupModel) throws Exception{
		ApexUserGroup apexUserGroupEntityToBeUpdated = null;
		try {
			Optional<ApexUserGroup> apexUserGroupEntityWrapper = apexUserGroupRepository
					.findById(apexUserGroupModel.getId());
			if (apexUserGroupEntityWrapper.isPresent()) {
				apexUserGroupEntityToBeUpdated = apexUserGroupEntityWrapper.get();
				apexUserGroupEntityToBeUpdated.setApexUserId(apexUserGroupModel.getApexUserId());
				apexUserGroupEntityToBeUpdated.setApexGroupId(apexUserGroupModel.getApexGroupId());
				apexUserGroupEntityToBeUpdated.setModifiedOn(currentDate);
				apexUserGroupEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				apexUserGroupEntityToBeUpdated.setActiveC(activeC);
				apexUserGroupRepository.save(apexUserGroupEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return apexUserGroupEntityToBeUpdated;
	}

	@Override
	public boolean deleteApexUserGroupByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			apexUserGroupRepository.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public ApexUserGroup getApexUserGroupByRecordId(Integer recordId) throws Exception{
		ApexUserGroup apexUserGroupEntityFetched = null;
		try {
			Optional<ApexUserGroup> apiUserGroupEntityWrapper = apexUserGroupRepository.findById(recordId);
			if (apiUserGroupEntityWrapper.isPresent())
				apexUserGroupEntityFetched = apiUserGroupEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return apexUserGroupEntityFetched;

	}

	@Override
	public List<ApexUserGroup> getAllApexUserGroups() throws Exception{
		List<ApexUserGroup> apexUserGroupList = null;
		try {
			apexUserGroupList = apexUserGroupRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
		return apexUserGroupList;

	}

}// End of ApexUserGroupServiceImpl
