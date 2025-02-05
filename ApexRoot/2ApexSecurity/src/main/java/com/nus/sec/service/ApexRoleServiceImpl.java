package com.nus.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.sec.entities.ApexRole;
import com.nus.sec.model.ApexRoleModel;
import com.nus.sec.repo.ApexRoleRepository;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:53:36 pm<br>
 * @Objective: <br>
 */
@Service
public class ApexRoleServiceImpl extends UserLoginBaseService implements ApexRoleService{

	@Autowired
	ApexRoleRepository apexRoleRepository;
	
	@Override
	public ApexRole addApexRole(ApexRoleModel apexRoleModel)throws Exception {
		ApexRole savedApexRoleEntity = null;
		try {
			ApexRole apexRoleEntity = new ApexRole();
			apexRoleEntity.setRoleName(apexRoleModel.getRoleName());
			apexRoleEntity.setCreatedOn(currentDate);
			apexRoleEntity.setCreatedBy(getCurrentLoginUserId());
			apexRoleEntity.setActiveC(activeC);
			savedApexRoleEntity = apexRoleRepository.save(apexRoleEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedApexRoleEntity;
	}

	@Override
	public ApexRole updateApexRole(ApexRoleModel apexRoleModel)throws Exception {
		ApexRole apexRoleEntityToBeUpdated = null;
		try {
			Optional<ApexRole> apexRoleEntityWrapper = apexRoleRepository.findById(apexRoleModel.getId());
			if (apexRoleEntityWrapper.isPresent()) {
				apexRoleEntityToBeUpdated = apexRoleEntityWrapper.get();
				apexRoleEntityToBeUpdated.setRoleName(apexRoleModel.getRoleName());								
				apexRoleEntityToBeUpdated.setModifiedOn(currentDate);
				apexRoleEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				apexRoleRepository.save(apexRoleEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return apexRoleEntityToBeUpdated;
	}

	@Override
	public boolean deleteApexRoleByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			apexRoleRepository.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;

	}

	@Override
	public ApexRole getApexRoleByRecordId(Integer recordId) throws Exception{
		ApexRole apexRoleEntityFetched = null;
		try {
			Optional<ApexRole> apexRoleEntityWrapper = apexRoleRepository.findById(recordId);
			if (apexRoleEntityWrapper.isPresent())
				apexRoleEntityFetched = apexRoleEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return apexRoleEntityFetched;
	}

	@Override
	public List<ApexRole> getAllApexRoles() throws Exception{
		List<ApexRole> apexRoleList = null;
		try {
			apexRoleList = apexRoleRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
		return apexRoleList;

	}

}
