package com.nus.pvt.admin.service;

import java.util.List;

import com.nus.pvt.admin.entities.AssignPmProjectToDm;
import com.nus.pvt.admin.model.AssignPmProjectToDmModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:43:36 pm<br>
 * @Objective: <br>
 */
public interface AssignPmProjectToDmService {
	
	public AssignPmProjectToDm addAssignPmProjectToDm(AssignPmProjectToDmModel assignPmProjectToDmModel)throws Exception;
	public AssignPmProjectToDm updateAssignPmProjectToDm(AssignPmProjectToDmModel assignPmProjectToDmModel)throws Exception;
	public boolean deleteAssignPmProjectToDmByRecordId(Integer recordId)throws Exception;
	public AssignPmProjectToDm getAssignPmProjectToDmByRecordId(Integer recordId)throws Exception;		
	public List<AssignPmProjectToDm> getAllAssignPmProjectToDms()throws Exception;

}
