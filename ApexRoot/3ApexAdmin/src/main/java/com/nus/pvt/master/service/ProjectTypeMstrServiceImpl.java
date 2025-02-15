package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.master.entities.ProjectTypeMstr;
import com.nus.pvt.master.model.ProjectTypeMstrModel;
import com.nus.pvt.master.repo.ProjectTypeMstrRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:32:25 pm<br>
 * @Objective: <br>
 */
@Service
public class ProjectTypeMstrServiceImpl extends UserLoginBaseService implements ProjectTypeMstrService {

	@Autowired
	ProjectTypeMstrRepo projectTypeMstrRepo;
	
	@Override
	public ProjectTypeMstr addProjectTypeMstr(ProjectTypeMstrModel projectTypeMstrModel)throws Exception {
		ProjectTypeMstr savedProjectTypeMstrEntity = null;
		try {
			ProjectTypeMstr projectTypeMstrEntity = new ProjectTypeMstr();
			projectTypeMstrEntity.setProjectType(projectTypeMstrModel.getProjectType());
			projectTypeMstrEntity.setCreatedOn(currentDate);
			projectTypeMstrEntity.setCreatedBy(getCurrentLoginUserId());
			projectTypeMstrEntity.setActiveC(activeC);
			savedProjectTypeMstrEntity = projectTypeMstrRepo.save(projectTypeMstrEntity);
		} catch (Exception ex) {
			throw ex;
		}
		return savedProjectTypeMstrEntity;
	}

	@Override
	public ProjectTypeMstr updateProjectTypeMstr(ProjectTypeMstrModel projectTypeMstrModel) throws Exception{
		ProjectTypeMstr projectTypeMstrEntityToBeUpdated = null;
		try {
			Optional<ProjectTypeMstr> projectTypeMstrEntityWrapper = projectTypeMstrRepo.findById(projectTypeMstrModel.getId());
			if (projectTypeMstrEntityWrapper.isPresent()) {
				projectTypeMstrEntityToBeUpdated = projectTypeMstrEntityWrapper.get();
				projectTypeMstrEntityToBeUpdated.setProjectType(projectTypeMstrModel.getProjectType());								
				projectTypeMstrEntityToBeUpdated.setModifiedOn(currentDate);
				projectTypeMstrEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				projectTypeMstrRepo.save(projectTypeMstrEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return projectTypeMstrEntityToBeUpdated;
	}

	@Override
	public boolean deleteProjectTypeMstrByRecordId(Integer recordId) throws Exception{
		boolean isRecordDeleted = true;
		try {
			projectTypeMstrRepo.deleteById(recordId);
		} catch (Exception ex) {			
			isRecordDeleted = false;
			throw ex;
		}
		return isRecordDeleted;
	}

	@Override
	public ProjectTypeMstr getProjectTypeMstrByRecordId(Integer recordId) throws Exception{
		ProjectTypeMstr projectTypeMstrEntityFetched = null;
		try {
			Optional<ProjectTypeMstr> projectTypeMstrEntityWrapper = projectTypeMstrRepo.findById(recordId);
			if (projectTypeMstrEntityWrapper.isPresent())
				projectTypeMstrEntityFetched = projectTypeMstrEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return projectTypeMstrEntityFetched;
	}

	@Override
	public List<ProjectTypeMstr> getAllProjectTypeMstrs() throws Exception{
		List<ProjectTypeMstr> projectTypeMstrList = null;
		try {
			projectTypeMstrList = projectTypeMstrRepo.findAllByActiveC("Y");
		} catch (Exception ex) {
			throw ex;
		}
		return projectTypeMstrList;
	}
	
}
