package com.nus.pvt.admin.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.model.AssignProjectToPmModel;
import com.nus.pvt.admin.repo.AssignProjectToPmRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:42:10 pm<br>
 * @Objective: <br>
 */
@Service
public class AssignProjectToPmServiceImpl extends UserLoginBaseService implements AssignProjectToPmService {

	private final Logger logger = LoggerFactory.getLogger(AssignProjectToPmServiceImpl.class);
	@Autowired
	AssignProjectToPmRepo assignProjectToPmRepo;

	@Override
	public AssignProjectToPm addAssignProjectToPm(AssignProjectToPmModel assignProjectToPmModel) throws Exception {
		AssignProjectToPm savedAssignProjectToPmEntity = null;
		try {
			AssignProjectToPm assignProjectToPmEntity = new AssignProjectToPm();
			
			assignProjectToPmEntity.setPmUserId(assignProjectToPmModel.getUserId());
			assignProjectToPmEntity.setClientAccountId(assignProjectToPmModel.getClientAccountId());
			assignProjectToPmEntity.setProjectCodeId(assignProjectToPmModel.getProjectCodeId());
			assignProjectToPmEntity.setProjectVerticalId(assignProjectToPmModel.getProjectVerticalId());
			assignProjectToPmEntity.setProjectTypeId(assignProjectToPmModel.getProjectTypeId());
			assignProjectToPmEntity.setAssignedOn(assignProjectToPmModel.getAssignedOn());
			
			assignProjectToPmEntity.setCreatedOn(currentDate);
			assignProjectToPmEntity.setCreatedBy(getCurrentLoginUserId());
			assignProjectToPmEntity.setActiveC(activeC);
			
			savedAssignProjectToPmEntity = assignProjectToPmRepo.save(assignProjectToPmEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedAssignProjectToPmEntity;
	}

	@Override
	public AssignProjectToPm updateAssignProjectToPm(AssignProjectToPmModel assignProjectToPmModel) throws Exception {
		AssignProjectToPm assignProjectToPmEntityToBeUpdated = null;
		try {
			Optional<AssignProjectToPm> assignProjectToPmEntityWrapper = assignProjectToPmRepo.findById(assignProjectToPmModel.getId());
			if (assignProjectToPmEntityWrapper.isPresent()) {
				assignProjectToPmEntityToBeUpdated = assignProjectToPmEntityWrapper.get();
				
				assignProjectToPmEntityToBeUpdated.setPmUserId(assignProjectToPmModel.getUserId());
				assignProjectToPmEntityToBeUpdated.setClientAccountId(assignProjectToPmModel.getClientAccountId());
				assignProjectToPmEntityToBeUpdated.setProjectCodeId(assignProjectToPmModel.getProjectCodeId());
				assignProjectToPmEntityToBeUpdated.setProjectVerticalId(assignProjectToPmModel.getProjectVerticalId());
				assignProjectToPmEntityToBeUpdated.setProjectTypeId(assignProjectToPmModel.getProjectTypeId());
				assignProjectToPmEntityToBeUpdated.setAssignedOn(assignProjectToPmModel.getAssignedOn());	
				
				assignProjectToPmEntityToBeUpdated.setModifiedOn(currentDate);
				assignProjectToPmEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());
				
				assignProjectToPmRepo.save(assignProjectToPmEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return assignProjectToPmEntityToBeUpdated;
	}

	@Override
	public boolean deleteAssignProjectToPmByRecordId(Integer recordId) throws Exception {
		boolean isRecordDeleted = true;
		try {
			assignProjectToPmRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public AssignProjectToPm getAssignProjectToPmByRecordId(Integer recordId) throws Exception {
		AssignProjectToPm assignProjectToPmEntityFetched = null;
		try {
			Optional<AssignProjectToPm> assignProjectToPmEntityWrapper = assignProjectToPmRepo.findById(recordId);
			if (assignProjectToPmEntityWrapper.isPresent())
				assignProjectToPmEntityFetched = assignProjectToPmEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return assignProjectToPmEntityFetched;
	}

	@Override
	public List<AssignProjectToPm> getAllAssignProjectToPms() throws Exception {
		List<AssignProjectToPm> assignProjectToPmList = null;
		try {
			assignProjectToPmList = assignProjectToPmRepo.findAllByActiveC("Y");
		} catch (Exception ex) {
			throw ex;
		}
		return assignProjectToPmList;
	}

}
