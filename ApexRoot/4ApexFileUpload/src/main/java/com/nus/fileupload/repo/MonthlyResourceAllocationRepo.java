package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.ProjectMonthlyResourceAllocation;
import com.nus.fileupload.model.ResourceAllocationModel;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:47:47 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MonthlyResourceAllocationRepo extends JpaRepository<ProjectMonthlyResourceAllocation, Integer>{

	@Query(value = "SELECT * FROM project_monthly_resource_allocation WHERE file_upload_date =?1", nativeQuery = true)
	List<ProjectMonthlyResourceAllocation> getAllByMonthAndYear(Date fileUploadDate);

	
	@Query(value = "SELECT DISTINCT emp_code, MAX(allocation_percentage) FROM project_monthly_resource_allocation"+  
			       " WHERE project_code_id =?1 "+
			       " AND file_upload_date >= ?2"+ 
			       " AND file_upload_date <=?3"+
			       " AND allocation_percentage > 0 "+
			       " Group by emp_code", nativeQuery = true)	
	List<ResourceAllocationModel> getMonthlyDistinctResourceAllocationBetweenMonths(int projectCodeId, 
			Date fFileUploadDate,Date tFileUploadDate);
	
	@Query(value = "SELECT * FROM project_monthly_resource_allocation"
			       + " WHERE project_code_id =?1"
				   + " AND file_upload_date =?2", nativeQuery = true)	
	List<ProjectMonthlyResourceAllocation> getDistinctResourceAllocationForCurrentMonth(int projectCodeId,
			Date fileUploadDate);

	@Query(value = "SELECT * FROM project_monthly_resource_allocation WHERE "
			+ " project_code_id =?1"
			+ " AND file_upload_date >=?2 AND file_upload_date <=?3"			
			+ " AND emp_status = 'Active' "
			+ " AND billing_status = 'Billable'", nativeQuery = true)	

	List<ProjectMonthlyResourceAllocation> getResourceAllocationForSpan_1AndSpan_2Calculation(int projectCodeId,
			Date fFileUploadDate,Date tFileUploadDate);

}
