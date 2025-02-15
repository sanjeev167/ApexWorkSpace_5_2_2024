package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.nus.fileupload.entities.ProjectMonthlyPlcc;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 26-Jan-2025<br>
 * @Time: 2:10:29 am<br>
 * @Objective: <br>
 */
public interface ProjectMonthlyPlccRepo extends JpaRepository<ProjectMonthlyPlcc, Integer>{	
	
	@Query(value = "SELECT * FROM project_monthly_plcc WHERE file_upload_date =?1", nativeQuery = true)
	List<ProjectMonthlyPlcc> getAllByMonthAndYear(Date plccMonthYear);

	@Query(value = "SELECT * FROM project_monthly_plcc WHERE project_code_id =?1"
			                                      + " AND file_upload_date >=?2"
			                                      + " AND file_upload_date <=?3", nativeQuery = true)
	List<ProjectMonthlyPlcc> getMonthlyPlccDataBetweenMonths(int projectCodeId, Date plccFMonthYear,Date plccTMonthYear);

}
