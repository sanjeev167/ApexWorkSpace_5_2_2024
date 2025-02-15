package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.CustomerSatisfaction;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:49:52 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MonthlyCustomerSatisfactionRepo extends JpaRepository<CustomerSatisfaction, Integer>{

	@Query(value = "SELECT * FROM customer_satisfaction WHERE file_upload_date =?1", nativeQuery = true)
	List<CustomerSatisfaction> getAllByMonthAndYear(Date fileUploadDate);

	@Query(value = "SELECT * FROM customer_satisfaction WHERE project_code_id =?1"
			                                  + " AND file_upload_date >=?2 AND file_upload_date <=?3", nativeQuery = true)	
	List<CustomerSatisfaction> getMonthlyCustomerSatisfactionBetweenMonths(int projectCodeId, Date fFileUploadDate,Date tFileUploadDate);

	

}
