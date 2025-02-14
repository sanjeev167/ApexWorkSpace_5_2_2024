package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.master.entities.ProjectVerticalMstr;
import com.nus.pvt.master.model.ProjectVerticalMstrModel;
import com.nus.pvt.master.repo.ProjectVerticalMstrRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:31:03 pm<br>
 * @Objective: <br>
 */
@Service
public class ProjectVerticalMstrServiceImpl extends UserLoginBaseService implements ProjectVerticalMstrService {

	@Autowired
	ProjectVerticalMstrRepo projectVerticalMstrRepo;
	
	
	@Override
	public ProjectVerticalMstr addProjectVerticalMstr(ProjectVerticalMstrModel projectVerticalMstrModel) throws Exception{
		ProjectVerticalMstr savedProjectVerticalMstrEntity = null;
		try {
			ProjectVerticalMstr projectVerticalMstrEntity = new ProjectVerticalMstr();
			projectVerticalMstrEntity.setVerticalName(projectVerticalMstrModel.getVerticalName());
			projectVerticalMstrEntity.setCreatedOn(currentDate);
			projectVerticalMstrEntity.setCreatedBy(getCurrentLoginUserId());
			projectVerticalMstrEntity.setActiveC("Y");
			savedProjectVerticalMstrEntity = projectVerticalMstrRepo.save(projectVerticalMstrEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedProjectVerticalMstrEntity;
	}

	@Override
	public ProjectVerticalMstr updateProjectVerticalMstr(ProjectVerticalMstrModel projectVerticalMstrModel) throws Exception{
		ProjectVerticalMstr projectVerticalMstrEntityToBeUpdated = null;
		try {
			Optional<ProjectVerticalMstr> projectVerticalMstrEntityWrapper = projectVerticalMstrRepo.findById(projectVerticalMstrModel.getId());
			if (projectVerticalMstrEntityWrapper.isPresent()) {
				projectVerticalMstrEntityToBeUpdated = projectVerticalMstrEntityWrapper.get();
				projectVerticalMstrEntityToBeUpdated.setVerticalName(projectVerticalMstrModel.getVerticalName());								
				projectVerticalMstrEntityToBeUpdated.setModifiedOn(currentDate);
				projectVerticalMstrEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				projectVerticalMstrRepo.save(projectVerticalMstrEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return projectVerticalMstrEntityToBeUpdated;
	}

	@Override
	public boolean deleteProjectVerticalMstrByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			projectVerticalMstrRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public ProjectVerticalMstr getProjectVerticalMstrByRecordId(Integer recordId) throws Exception{
		ProjectVerticalMstr projectVerticalMstrEntityFetched = null;
		try {
			Optional<ProjectVerticalMstr> projectVerticalMstrEntityWrapper = projectVerticalMstrRepo.findById(recordId);
			if (projectVerticalMstrEntityWrapper.isPresent())
				projectVerticalMstrEntityFetched = projectVerticalMstrEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return projectVerticalMstrEntityFetched;
	}

	@Override
	public List<ProjectVerticalMstr> getAllProjectVerticalMstrs() {
		List<ProjectVerticalMstr> projectVerticalMstrList = null;
		try {
			projectVerticalMstrList = projectVerticalMstrRepo.findAllByActiveC("Y");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return projectVerticalMstrList;
	}
}
