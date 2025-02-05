package com.nus.sec.service;

import java.util.List;

import com.nus.sec.entities.ApexGroup;
import com.nus.sec.model.ApexGroupModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:21:24 pm<br>
 * @Objective: <br>
 */
public interface ApexGroupService {
	
	public ApexGroup addApexGroup(ApexGroupModel apexGroupModel)throws Exception;

	public ApexGroup updateApexGroup(ApexGroupModel apexGroupModel)throws Exception;

	public boolean deleteApexGroupByRecordId(Integer recordId)throws Exception;

	public ApexGroup getApexGroupByRecordId(Integer recordId)throws Exception;

	public List<ApexGroup> getAllApexGroups()throws Exception;

}
