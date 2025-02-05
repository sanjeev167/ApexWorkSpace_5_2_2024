package com.nus.sec.service;

import java.util.List;

import com.nus.sec.entities.ApexRole;
import com.nus.sec.model.ApexRoleModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:52:03 pm<br>
 * @Objective: <br>
 */
public interface ApexRoleService{
	public ApexRole addApexRole(ApexRoleModel apexRoleModel)throws Exception;
	public ApexRole updateApexRole(ApexRoleModel apexRoleModel)throws Exception;
	public boolean deleteApexRoleByRecordId(Integer recordId)throws Exception;
	public ApexRole getApexRoleByRecordId(Integer recordId)throws Exception;
	public List<ApexRole> getAllApexRoles()throws Exception;

}
