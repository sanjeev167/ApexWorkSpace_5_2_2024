package com.nus.pvt.master.service;

import java.util.List;

import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.pvt.master.model.ProjectCodeMstrModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:33:00 pm<br>
 * @Objective: <br>
 */
public interface ProjectCodeMstrService {
	
	public ProjectCodeMstr addProjectCodeMstr(ProjectCodeMstrModel projectCodeMstrModel) throws Exception;
	public ProjectCodeMstr updateProjectCodeMstr(ProjectCodeMstrModel projectCodeMstrModel)throws Exception;
	public boolean deleteProjectCodeMstrByRecordId(Integer recordId)throws Exception;
	public ProjectCodeMstr getProjectCodeMstrByRecordId(Integer recordId)throws Exception;
	public List<ProjectCodeMstr> getAllProjectCodeMstrs()throws Exception;


}
