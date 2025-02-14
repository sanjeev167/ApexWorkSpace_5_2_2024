package com.nus.pvt.admin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.admin.entities.AssignDmProjectToPractice;
import com.nus.pvt.admin.model.AssignDmProjectToPracticeModel;
import com.nus.pvt.admin.repo.AssignDmProjectToPracticeRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:44:54 pm<br>
 * @Objective: <br>
 */
@Service
public class AssignDmProjectToPracticeServiceImpl extends UserLoginBaseService implements AssignDmProjectToPracticeService {

	@Autowired
	AssignDmProjectToPracticeRepo assignDmProjectToPracticeRepo;

	

	@Override
	public AssignDmProjectToPractice addAssignDmProjectToPractice(AssignDmProjectToPracticeModel assignDmProjectToPracticeModel) throws Exception {
		AssignDmProjectToPractice savedAssignDmProjectToPracticeEntity = null;
		try {
			AssignDmProjectToPractice assignDmProjectToPracticeEntity = new AssignDmProjectToPractice();
			
			assignDmProjectToPracticeEntity.setPracticeUserId(assignDmProjectToPracticeModel.getUserId());
			assignDmProjectToPracticeEntity.setAssignedDmProjectId(assignDmProjectToPracticeModel.getProjectAssignedToDmId());
			assignDmProjectToPracticeEntity.setAssignedOn(assignDmProjectToPracticeModel.getAssignedOn());		
			
			assignDmProjectToPracticeEntity.setCreatedOn(currentDate);
			assignDmProjectToPracticeEntity.setCreatedBy(getCurrentLoginUserId());
			assignDmProjectToPracticeEntity.setActiveC(activeC);
			
			savedAssignDmProjectToPracticeEntity = assignDmProjectToPracticeRepo.save(assignDmProjectToPracticeEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedAssignDmProjectToPracticeEntity;
	}

	@Override
	public AssignDmProjectToPractice updateAssignDmProjectToPractice(AssignDmProjectToPracticeModel assignDmProjectToPracticeModel) throws Exception {
		AssignDmProjectToPractice assignDmProjectToPracticeEntityToBeUpdated = null;
		try {
			Optional<AssignDmProjectToPractice> assignDmProjectToPracticeEntityWrapper = assignDmProjectToPracticeRepo.findById(assignDmProjectToPracticeModel.getId());
			if (assignDmProjectToPracticeEntityWrapper.isPresent()) {
				assignDmProjectToPracticeEntityToBeUpdated = assignDmProjectToPracticeEntityWrapper.get();
				
				assignDmProjectToPracticeEntityToBeUpdated.setPracticeUserId(assignDmProjectToPracticeModel.getUserId());
				assignDmProjectToPracticeEntityToBeUpdated.setAssignedDmProjectId(assignDmProjectToPracticeModel.getProjectAssignedToDmId());
				assignDmProjectToPracticeEntityToBeUpdated.setAssignedOn(assignDmProjectToPracticeModel.getAssignedOn());	
				
				assignDmProjectToPracticeEntityToBeUpdated.setModifiedOn(currentDate);
				assignDmProjectToPracticeEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				
				assignDmProjectToPracticeRepo.save(assignDmProjectToPracticeEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return assignDmProjectToPracticeEntityToBeUpdated;
	}

	@Override
	public boolean deleteAssignDmProjectToPracticeByRecordId(Integer recordId) throws Exception {
		boolean isRecordDeleted = true;
		try {
			assignDmProjectToPracticeRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public AssignDmProjectToPractice getAssignDmProjectToPracticeByRecordId(Integer recordId) throws Exception {
		AssignDmProjectToPractice assignDmProjectToPracticeEntityFetched = null;
		try {
			Optional<AssignDmProjectToPractice> assignDmProjectToPracticeEntityWrapper = assignDmProjectToPracticeRepo.findById(recordId);
			if (assignDmProjectToPracticeEntityWrapper.isPresent())
				assignDmProjectToPracticeEntityFetched = assignDmProjectToPracticeEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return assignDmProjectToPracticeEntityFetched;
	}

	@Override
	public List<AssignDmProjectToPractice> getAllAssignDmProjectToPractices() throws Exception {
		List<AssignDmProjectToPractice> assignDmProjectToPracticeList = null;
		try {
			assignDmProjectToPracticeList = assignDmProjectToPracticeRepo.findAllByActiveC(activeC);
		} catch (Exception ex) {
			throw ex;
		}
		return assignDmProjectToPracticeList;
	}
	
}
