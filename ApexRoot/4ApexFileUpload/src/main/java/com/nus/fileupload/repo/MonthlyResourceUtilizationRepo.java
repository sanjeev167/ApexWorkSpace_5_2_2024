package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.ProjectMonthlyResourceUtilization;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:45:16 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MonthlyResourceUtilizationRepo extends JpaRepository<ProjectMonthlyResourceUtilization, Integer>{

	@Query(value = "SELECT * FROM project_monthly_resource_utilization WHERE file_upload_date =?1 ", nativeQuery = true)
	List<ProjectMonthlyResourceUtilization> getAllByMonthAndYear(Date fileUploadDate);
	
	@Query(value = "SELECT * FROM project_monthly_resource_utilization WHERE project_code_id = ?1"
			                          + " AND file_upload_date >=?2 AND file_upload_date <=?3", nativeQuery = true)	
	List<ProjectMonthlyResourceUtilization> getMonthlyResourceUtilizationBetweenMonths(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate);

	@Query(value = "SELECT * FROM project_monthly_resource_utilization WHERE "
			+ " project_code_id = ?1 AND file_upload_date =?2 ", nativeQuery = true)	
	ProjectMonthlyResourceUtilization getAMonthSpecificResourceUtilization(int projectCodeId,Date fileUploadDate);

}
