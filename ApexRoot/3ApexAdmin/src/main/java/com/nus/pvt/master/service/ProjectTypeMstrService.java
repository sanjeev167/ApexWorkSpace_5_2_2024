package com.nus.pvt.master.service;

import java.util.List;

import com.nus.pvt.master.entities.ProjectTypeMstr;
import com.nus.pvt.master.model.ProjectTypeMstrModel;



/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:32:12 pm<br>
 * @Objective: <br>
 */
public interface ProjectTypeMstrService {

	public ProjectTypeMstr addProjectTypeMstr(ProjectTypeMstrModel projectTypeMstrModel)throws Exception;
	public ProjectTypeMstr updateProjectTypeMstr(ProjectTypeMstrModel projectTypeMstrModel)throws Exception;
	public boolean deleteProjectTypeMstrByRecordId(Integer recordId)throws Exception;
	public ProjectTypeMstr getProjectTypeMstrByRecordId(Integer recordId)throws Exception;
	public List<ProjectTypeMstr> getAllProjectTypeMstrs()throws Exception;
}
