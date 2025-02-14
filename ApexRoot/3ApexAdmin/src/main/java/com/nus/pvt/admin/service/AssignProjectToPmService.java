package com.nus.pvt.admin.service;

import java.util.List;

import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.model.AssignProjectToPmModel;


/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:41:58 pm<br>
 * @Objective: <br>
 */
public interface AssignProjectToPmService {
	
	public AssignProjectToPm addAssignProjectToPm(AssignProjectToPmModel assignProjectToPmModel)throws Exception;
	public AssignProjectToPm updateAssignProjectToPm(AssignProjectToPmModel assignProjectToPmModel)throws Exception;
	public boolean deleteAssignProjectToPmByRecordId(Integer recordId)throws Exception;
	public AssignProjectToPm getAssignProjectToPmByRecordId(Integer recordId)throws Exception;		
	public List<AssignProjectToPm> getAllAssignProjectToPms()throws Exception;
		
}
