package com.nus.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.sec.entities.ApexGroup;
import com.nus.sec.model.ApexGroupModel;
import com.nus.sec.repo.ApexGroupRepository;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:21:39 pm<br>
 * @Objective: <br>
 */
@Service
public class ApexGroupServiceImpl extends UserLoginBaseService implements ApexGroupService{

	@Autowired
	ApexGroupRepository apexGroupRepository;

	@Override
	public ApexGroup addApexGroup(ApexGroupModel apexGroupModel) throws Exception{
		ApexGroup savedApexGroupEntity = null;
		try {
			ApexGroup apexGroupEntity = new ApexGroup();
			apexGroupEntity.setGroupName(apexGroupModel.getGroupName());
			apexGroupEntity.setCreatedOn(currentDate);
			apexGroupEntity.setCreatedBy(getCurrentLoginUserId());
			apexGroupEntity.setActiveC(activeC);
			savedApexGroupEntity = apexGroupRepository.save(apexGroupEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedApexGroupEntity;

	}

	@Override
	public ApexGroup updateApexGroup(ApexGroupModel apexGroupModel) throws Exception{
		ApexGroup apexGroupEntityToBeUpdated = null;
		try {			
			Optional<ApexGroup> apiGroupEntityWrapper = apexGroupRepository.findById(apexGroupModel.getId());
			if (apiGroupEntityWrapper.isPresent()) {
				apexGroupEntityToBeUpdated = apiGroupEntityWrapper.get();
				apexGroupEntityToBeUpdated.setGroupName(apexGroupModel.getGroupName());
				apexGroupEntityToBeUpdated.setModifiedOn(currentDate);
				apexGroupEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				apexGroupEntityToBeUpdated.setActiveC(activeC);
				apexGroupRepository.save(apexGroupEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return apexGroupEntityToBeUpdated;

	}

	@Override
	public boolean deleteApexGroupByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			apexGroupRepository.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public ApexGroup getApexGroupByRecordId(Integer recordId) throws Exception{
		ApexGroup apexGroupEntityFetched = null;
		try {
			Optional<ApexGroup> apexGroupEntityWrapper = apexGroupRepository.findById(recordId);
			if (apexGroupEntityWrapper.isPresent())
				apexGroupEntityFetched = apexGroupEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return apexGroupEntityFetched;

	}

	@Override
	public List<ApexGroup> getAllApexGroups() throws Exception{
		List<ApexGroup> apexGroupList = null;
		try {
			apexGroupList = apexGroupRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
		return apexGroupList;

	}
}// End of ApexGroupServiceImpl

