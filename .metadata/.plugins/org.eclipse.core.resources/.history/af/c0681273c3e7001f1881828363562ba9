package com.nus.fileupload.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.ProjectMonthlyResourceAllocation;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:47:47 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MonthlyResourceAllocationRepo extends JpaRepository<ProjectMonthlyResourceAllocation, Integer>{

	@Query(value = "SELECT * FROM project_monthly_resource_allocation WHERE allocation_month =?1 AND allocation_year =?2", nativeQuery = true)
	List<ProjectMonthlyResourceAllocation> getAllByMonthAndYear(Integer month, Integer year);

}
