package com.nus.pvt.master.service;

import java.util.List;

import com.nus.pvt.master.entities.ProjectVerticalMstr;
import com.nus.pvt.master.model.ProjectVerticalMstrModel;


/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:30:44 pm<br>
 * @Objective: <br>
 */
public interface ProjectVerticalMstrService {

	public ProjectVerticalMstr addProjectVerticalMstr(ProjectVerticalMstrModel projectVerticalMstrModel)throws Exception;
	public ProjectVerticalMstr updateProjectVerticalMstr(ProjectVerticalMstrModel projectVerticalMstrModel)throws Exception;
	public boolean deleteProjectVerticalMstrByRecordId(Integer recordId)throws Exception;
	public ProjectVerticalMstr getProjectVerticalMstrByRecordId(Integer recordId)throws Exception;
	public List<ProjectVerticalMstr> getAllProjectVerticalMstrs()throws Exception;
}
