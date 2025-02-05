package com.nus.sec.service;

import java.util.List;

import com.nus.sec.entities.ApexGroupRole;
import com.nus.sec.model.ApexGroupRoleModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 4:30:45 pm<br>
 * @Objective: <br>
 */
public interface ApexGroupRoleService {
	public ApexGroupRole addApexGroupRole(ApexGroupRoleModel apexGroupRoleModel)throws Exception;

	public ApexGroupRole updateApexGroupRole(ApexGroupRoleModel apexGroupRoleModel)throws Exception;

	public boolean deleteApexGroupRoleByRecordId(Integer recordId)throws Exception;

	public ApexGroupRole getApexGroupRoleByRecordId(Integer recordId)throws Exception;

	public List<ApexGroupRole> getAllApexGroupRoles()throws Exception;
}//End of ApexGroupRoleService
