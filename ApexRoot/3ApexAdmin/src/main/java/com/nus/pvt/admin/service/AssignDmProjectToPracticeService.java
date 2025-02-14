package com.nus.pvt.admin.service;

import java.util.List;

import com.nus.pvt.admin.entities.AssignDmProjectToPractice;
import com.nus.pvt.admin.model.AssignDmProjectToPracticeModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:44:42 pm<br>
 * @Objective: <br>
 */
public interface AssignDmProjectToPracticeService {

	public AssignDmProjectToPractice addAssignDmProjectToPractice(AssignDmProjectToPracticeModel assignDmProjectToPracticeModel)throws Exception;
	public AssignDmProjectToPractice updateAssignDmProjectToPractice(AssignDmProjectToPracticeModel assignDmProjectToPracticeModel)throws Exception;
	public boolean deleteAssignDmProjectToPracticeByRecordId(Integer recordId)throws Exception;
	public AssignDmProjectToPractice getAssignDmProjectToPracticeByRecordId(Integer recordId)throws Exception;		
	public List<AssignDmProjectToPractice> getAllAssignDmProjectToPractices()throws Exception;

}
