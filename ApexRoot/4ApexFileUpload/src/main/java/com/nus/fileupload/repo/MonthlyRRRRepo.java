package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.ProjectMonthlyRrrr;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:43:42 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MonthlyRRRRepo extends JpaRepository<ProjectMonthlyRrrr, Integer>{

	@Query(value = "SELECT * FROM project_monthly_rrrr WHERE file_upload_date =?1", nativeQuery = true)	
	List<ProjectMonthlyRrrr> getAllByMonthAndYear(Date fileUploadDate);	

	@Query(value = "SELECT * FROM project_monthly_rrrr WHERE project_code_id=?1"
			                                       + " AND file_upload_date >=?2 AND file_upload_date <=?3", nativeQuery = true)	
	List<ProjectMonthlyRrrr> getMonthlyRRRRBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate);

	@Query(value = "SELECT  COUNT(expected_increase_pm) FROM project_monthly_rrrr WHERE project_code_id=?1"
                                               + " AND file_upload_date >=?2 AND file_upload_date <=?3"
                                               + " AND expected_increase_pm>0", nativeQuery = true)
	Integer getMonthlyExpectedIncreasePM_RRRR(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate);

	@Query(value = "SELECT  * FROM project_monthly_rrrr WHERE project_code_id=?1"
            + " AND file_upload_date >=?2 AND file_upload_date <=?3"
            + " AND expected_increase_pm>0", nativeQuery = true)
	List<ProjectMonthlyRrrr> getRRRRPotentialRevenuePerMonth(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate);

	
}
