package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.ProjectMonthlyResourceAttrition;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:53:25 pm<br>
 * @Objective: <br>
 */
 @Repository
public interface MonthlyAttritionRepo extends JpaRepository<ProjectMonthlyResourceAttrition, Integer>{

	 @Query(value = "SELECT * FROM project_monthly_resource_attrition WHERE "
	 		+ "file_upload_date =?1", nativeQuery = true)
	List<ProjectMonthlyResourceAttrition> getAllByMonthAndYear(Date fileUploadDate);
	 
	 
	 
	 @Query(value = "SELECT * FROM project_monthly_resource_attrition WHERE project_code_id =?1"
		 		+ " AND file_upload_date >=?2 AND file_upload_date <=?3 ", nativeQuery = true)
	 List<ProjectMonthlyResourceAttrition> getMonthlyAttritionDataBetweenMonths(int projectCodeId, Date fFileUploadDate,
				Date tFileUploadDate);
	 
	 @Query(value = "SELECT * FROM project_monthly_resource_attrition WHERE project_code_id =?1 and "
	 		+ "file_upload_date =?2", nativeQuery = true)
	 List<ProjectMonthlyResourceAttrition> getAMonthSpecificAttrition(Integer projectCodeId,Date currentMonthYearDate);

	

}
