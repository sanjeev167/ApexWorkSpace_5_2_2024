package com.nus.sec.service;

import java.util.List;

import com.nus.sec.entities.ApexUser;
import com.nus.sec.model.ApexUserModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 10:17:36 pm<br>
 * @Objective: <br>
 */
public interface ApexUserService {
	public ApexUser addApexUser(ApexUserModel apexUserModel)throws Exception;
	public ApexUser updateApexUser(ApexUserModel apexUserModel)throws Exception;
	public boolean deleteApexUserByRecordId(Integer recordId)throws Exception;
	public ApexUser getApexUserByRecordId(Integer recordId)throws Exception;
	public Integer findByEmail(String email)throws Exception;	
	public List<ApexUser> getAllApexUsers()throws Exception;

}
