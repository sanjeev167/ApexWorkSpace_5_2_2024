package com.nus.fileupload.repo;

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

	@Query(value = "SELECT * FROM project_monthly_rrrr WHERE rrrr_month =?1 AND rrrr_year =?2", nativeQuery = true)
	List<ProjectMonthlyRrrr> getAllByMonthAndYear(int month, int year);	
}
