package com.nus.pvt.master.service;

import java.util.List;

import com.nus.pvt.master.model.UserGradeModel;
import com.nus.sec.entities.UserGrade;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:28:55 pm<br>
 * @Objective: <br>
 */
public interface UserGradeService {
	
	public UserGrade addUserGrade(UserGradeModel userGradeModel)throws Exception;
	public UserGrade updateUserGrade(UserGradeModel userGradeModel)throws Exception;
	public boolean deleteUserGradeByRecordId(Integer recordId)throws Exception;
	public UserGrade getUserGradeByRecordId(Integer recordId)throws Exception;
	public List<UserGrade> getAllUserGrades()throws Exception;

}
