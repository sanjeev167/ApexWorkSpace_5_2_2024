package com.nus.pvt.admin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nus.pvt.admin.entities.AssignProjectToPm;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:53:49 pm<br>
 * @Objective: <br>
 */
@Repository
public interface AssignProjectToPmRepo extends JpaRepository<AssignProjectToPm, Integer> {

	List<AssignProjectToPm> findAllByActiveC(String string);
	
	@Query(value = "Select * from assign_project_to_pm where pm_user_id=?1 and active_c=?2", nativeQuery = true)	
	List<AssignProjectToPm> findByPm(Integer currentLoginUserId, String activeC);

}
