package com.nus.pvt.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.admin.entities.AssignPmProjectToDm;
import com.nus.pvt.admin.model.AssignPmProjectToDmModel;
import com.nus.pvt.admin.repo.AssignPmProjectToDmRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:43:54 pm<br>
 * @Objective: <br>
 */
@Service
public class AssignPmProjectToDmServiceImpl extends UserLoginBaseService implements AssignPmProjectToDmService {

	@Autowired
	AssignPmProjectToDmRepo assignPmProjectToDmRepo;

	

	@Override
	public AssignPmProjectToDm addAssignPmProjectToDm(AssignPmProjectToDmModel assignPmProjectToDmModel) throws Exception {
		AssignPmProjectToDm savedAssignPmProjectToDmEntity = null;
		try {
			AssignPmProjectToDm assignPmProjectToDmEntity = new AssignPmProjectToDm();
			
			assignPmProjectToDmEntity.setDmUserId(assignPmProjectToDmModel.getUserId());
			assignPmProjectToDmEntity.setAssignedPmProjectId(assignPmProjectToDmModel.getProjectAssignedToPmId());
			assignPmProjectToDmEntity.setAssignedOn(assignPmProjectToDmModel.getAssignedOn());		
			
			assignPmProjectToDmEntity.setCreatedOn(currentDate);
			assignPmProjectToDmEntity.setCreatedBy(getCurrentLoginUserId());
			assignPmProjectToDmEntity.setActiveC(activeC);
			
			savedAssignPmProjectToDmEntity = assignPmProjectToDmRepo.save(assignPmProjectToDmEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedAssignPmProjectToDmEntity;
	}

	@Override
	public AssignPmProjectToDm updateAssignPmProjectToDm(AssignPmProjectToDmModel assignPmProjectToDmModel) throws Exception {
		AssignPmProjectToDm assignPmProjectToDmEntityToBeUpdated = null;
		try {
			Optional<AssignPmProjectToDm> assignPmProjectToDmEntityWrapper = assignPmProjectToDmRepo.findById(assignPmProjectToDmModel.getId());
			if (assignPmProjectToDmEntityWrapper.isPresent()) {
				assignPmProjectToDmEntityToBeUpdated = assignPmProjectToDmEntityWrapper.get();
				
				assignPmProjectToDmEntityToBeUpdated.setDmUserId(assignPmProjectToDmModel.getUserId());
				assignPmProjectToDmEntityToBeUpdated.setAssignedPmProjectId(assignPmProjectToDmModel.getProjectAssignedToPmId());
				assignPmProjectToDmEntityToBeUpdated.setAssignedOn(assignPmProjectToDmModel.getAssignedOn());	
				
				assignPmProjectToDmEntityToBeUpdated.setModifiedOn(currentDate);
				assignPmProjectToDmEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				
				assignPmProjectToDmRepo.save(assignPmProjectToDmEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return assignPmProjectToDmEntityToBeUpdated;
	}

	@Override
	public boolean deleteAssignPmProjectToDmByRecordId(Integer recordId) throws Exception {
		boolean isRecordDeleted = true;
		try {
			assignPmProjectToDmRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public AssignPmProjectToDm getAssignPmProjectToDmByRecordId(Integer recordId) throws Exception {
		AssignPmProjectToDm assignPmProjectToDmEntityFetched = null;
		try {
			Optional<AssignPmProjectToDm> assignPmProjectToDmEntityWrapper = assignPmProjectToDmRepo.findById(recordId);
			if (assignPmProjectToDmEntityWrapper.isPresent())
				assignPmProjectToDmEntityFetched = assignPmProjectToDmEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return assignPmProjectToDmEntityFetched;
	}

	@Override
	public List<AssignPmProjectToDm> getAllAssignPmProjectToDms() throws Exception {
		List<AssignPmProjectToDm> assignPmProjectToDmList = null;
		try {
			assignPmProjectToDmList = assignPmProjectToDmRepo.findAllByActiveC(activeC);
		} catch (Exception ex) {
			throw ex;
		}
		return assignPmProjectToDmList;
	}
	
}
