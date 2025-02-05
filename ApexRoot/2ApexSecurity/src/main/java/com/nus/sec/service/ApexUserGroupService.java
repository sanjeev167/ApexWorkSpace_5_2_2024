package com.nus.sec.service;

import java.util.List;

import com.nus.sec.entities.ApexUserGroup;
import com.nus.sec.model.ApexUserGroupModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 10:04:41 pm<br>
 * @Objective: <br>
 */
public interface ApexUserGroupService {
	public ApexUserGroup addApexUserGroup(ApexUserGroupModel apexUserGroupModel)throws Exception;

	public ApexUserGroup updateApexUserGroup(ApexUserGroupModel apexUserGroupModel)throws Exception;

	public boolean deleteApexUserGroupByRecordId(Integer recordId)throws Exception;

	public ApexUserGroup getApexUserGroupByRecordId(Integer recordId)throws Exception;

	public List<ApexUserGroup> getAllApexUserGroups()throws Exception;

}
