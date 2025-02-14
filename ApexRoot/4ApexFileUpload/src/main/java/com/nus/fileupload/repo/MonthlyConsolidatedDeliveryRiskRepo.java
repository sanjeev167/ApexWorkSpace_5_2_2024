package com.nus.fileupload.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.ConsolidatedDeliveryRisk;

/**
 * @Author:SanjeevKumar<br>
 * @Date:24-Jan-2025<br>
 * @Time:10:51:37 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MonthlyConsolidatedDeliveryRiskRepo extends JpaRepository<ConsolidatedDeliveryRisk, Integer>{

	@Query(value = "SELECT * FROM consolidated_delivery_risk WHERE file_upload_date =?1", nativeQuery = true)
	List<ConsolidatedDeliveryRisk> getAllByMonthAndYear(Date fileUploadDate);

	@Query(value = "SELECT * FROM consolidated_delivery_risk WHERE project_code_id =?1"
			                                 + " AND file_upload_date >=?2 AND file_upload_date <=?3", nativeQuery = true)	
	List<ConsolidatedDeliveryRisk> getMonthlyConsolidatedDeliveryRiskBetweenMonths(int projectCodeId,Date fFileUploadDate,Date tFileUploadDate);

}
