package com.nus.sec.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.sec.entities.ApexGroupRole;
import com.nus.sec.model.ApexGroupRoleModel;
import com.nus.sec.repo.ApexGroupRoleRepository;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:31:13 pm<br>
 * @Objective: <br>
 */
@Service
public class ApexGroupRoleServiceImpl extends UserLoginBaseService implements ApexGroupRoleService{
	
	@Autowired
	ApexGroupRoleRepository apexGroupRoleRepository;

	@Override
	public ApexGroupRole addApexGroupRole(ApexGroupRoleModel apexGroupRoleModel) throws Exception{
		ApexGroupRole savedApexGroupRoleEntity = null;
		try {
			ApexGroupRole apexApiGroupRoleEntity = new ApexGroupRole();
			apexApiGroupRoleEntity.setApexRoleId(apexGroupRoleModel.getApexRoleId());
			apexApiGroupRoleEntity.setApexGroupId(apexGroupRoleModel.getApexGroupId());			
			apexApiGroupRoleEntity.setCreatedOn(currentDate);
			apexApiGroupRoleEntity.setCreatedBy(getCurrentLoginUserId());
			apexApiGroupRoleEntity.setActiveC("Y");
			savedApexGroupRoleEntity = apexGroupRoleRepository.save(apexApiGroupRoleEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedApexGroupRoleEntity;

	}

	@Override
	public ApexGroupRole updateApexGroupRole(ApexGroupRoleModel apexGroupRoleModel)throws Exception {
		ApexGroupRole apexGroupRoleEntityToBeUpdated = null;
		try {
			Optional<ApexGroupRole> apexGroupRoleEntityWrapper = apexGroupRoleRepository.findById(apexGroupRoleModel.getId());
			if (apexGroupRoleEntityWrapper.isPresent()) {
				apexGroupRoleEntityToBeUpdated = apexGroupRoleEntityWrapper.get();
				apexGroupRoleEntityToBeUpdated.setApexRoleId(apexGroupRoleModel.getApexRoleId());
				apexGroupRoleEntityToBeUpdated.setApexGroupId(apexGroupRoleModel.getApexGroupId());
				apexGroupRoleEntityToBeUpdated.setModifiedOn(currentDate);
				apexGroupRoleEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				apexGroupRoleEntityToBeUpdated.setActiveC(activeC);
				apexGroupRoleRepository.save(apexGroupRoleEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return apexGroupRoleEntityToBeUpdated;

	}

	@Override
	public boolean deleteApexGroupRoleByRecordId(Integer recordId)throws Exception {
		boolean isRecordDeleted = true;
		try {
			apexGroupRoleRepository.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;

	}

	@Override
	public ApexGroupRole getApexGroupRoleByRecordId(Integer recordId)throws Exception {
		ApexGroupRole apexGroupRoleEntityFetched = null;
		try {
			Optional<ApexGroupRole> apexGroupRoleEntityWrapper = apexGroupRoleRepository.findById(recordId);
			if (apexGroupRoleEntityWrapper.isPresent())
				apexGroupRoleEntityFetched = apexGroupRoleEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return apexGroupRoleEntityFetched;

	}

	@Override
	public List<ApexGroupRole> getAllApexGroupRoles() throws Exception{
		List<ApexGroupRole> apexGroupRoleList = null;
		try {
			apexGroupRoleList = apexGroupRoleRepository.findAll();
		} catch (Exception ex) {
			throw ex;
		}
		return apexGroupRoleList;

	}

}
