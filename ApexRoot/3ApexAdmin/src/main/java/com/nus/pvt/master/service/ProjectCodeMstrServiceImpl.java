package com.nus.pvt.master.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.pvt.master.model.ProjectCodeMstrModel;
import com.nus.pvt.master.repo.ProjectCodeMstrRepo;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:33:15 pm<br>
 * @Objective: <br>
 */
@Service
public class ProjectCodeMstrServiceImpl extends UserLoginBaseService implements ProjectCodeMstrService {

	@Autowired
	ProjectCodeMstrRepo projectCodeMstrRepo;
	
	@Override
	public ProjectCodeMstr addProjectCodeMstr(ProjectCodeMstrModel projectCodeMstrModel) throws Exception{
		ProjectCodeMstr savedProjectCodeMstrEntity = null;
		try {
			ProjectCodeMstr projectCodeMstrEntity = new ProjectCodeMstr();
			projectCodeMstrEntity.setProjectCode(projectCodeMstrModel.getProjectCode());
			projectCodeMstrEntity.setProjectName(projectCodeMstrModel.getProjectName());
			projectCodeMstrEntity.setCreatedOn(currentDate);
			projectCodeMstrEntity.setCreatedBy(getCurrentLoginUserId());
			projectCodeMstrEntity.setActiveC("Y");
			savedProjectCodeMstrEntity = projectCodeMstrRepo.save(projectCodeMstrEntity);
		} 
		catch (Exception ex) {
			throw ex;
		}
		return savedProjectCodeMstrEntity;
	}

	@Override
	public ProjectCodeMstr updateProjectCodeMstr(ProjectCodeMstrModel projectCodeMstrModel) throws Exception{
		ProjectCodeMstr projectCodeMstrEntityToBeUpdated = null;
		try {
			Optional<ProjectCodeMstr> projectCodeMstrEntityWrapper = projectCodeMstrRepo.findById(projectCodeMstrModel.getId());
			if (projectCodeMstrEntityWrapper.isPresent()) {
				projectCodeMstrEntityToBeUpdated = projectCodeMstrEntityWrapper.get();
				projectCodeMstrEntityToBeUpdated.setProjectCode(projectCodeMstrModel.getProjectCode());	
				projectCodeMstrEntityToBeUpdated.setProjectName(projectCodeMstrModel.getProjectName());
				projectCodeMstrEntityToBeUpdated.setModifiedOn(currentDate);
				projectCodeMstrEntityToBeUpdated.setModifiedBy(getCurrentLoginUserId());				
				projectCodeMstrRepo.save(projectCodeMstrEntityToBeUpdated);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return projectCodeMstrEntityToBeUpdated;
	}

	@Override
	public boolean deleteProjectCodeMstrByRecordId(Integer recordId)throws Exception {
		boolean isRecordDeleted = true;
		try {
			projectCodeMstrRepo.deleteById(recordId);
		} catch (Exception ex) {
			isRecordDeleted = false;
			throw ex;			
		}
		return isRecordDeleted;
	}

	@Override
	public ProjectCodeMstr getProjectCodeMstrByRecordId(Integer recordId) throws Exception{
		ProjectCodeMstr projectCodeMstrEntityFetched = null;
		try {
			Optional<ProjectCodeMstr> projectCodeMstrEntityWrapper = projectCodeMstrRepo.findById(recordId);
			if (projectCodeMstrEntityWrapper.isPresent())
				projectCodeMstrEntityFetched = projectCodeMstrEntityWrapper.get();
		} catch (Exception ex) {
			throw ex;
		}
		return projectCodeMstrEntityFetched;
	}

	@Override
	public List<ProjectCodeMstr> getAllProjectCodeMstrs() throws Exception{
		List<ProjectCodeMstr> projectCodeMstrList = null;
		try {
			projectCodeMstrList = projectCodeMstrRepo.findAllByActiveC("Y");
		} catch (Exception ex) {
			throw ex;
		}
		return projectCodeMstrList;
	}
	
}
