package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.HprojectMonthlyResourceAllocation;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 02-Feb-2025<br>
 * @Time: 2:23:57 am<br>
 * @Objective: <br>
 */
@Repository
public interface HprojectMonthlyResourceAllocationRepo extends JpaRepository<HprojectMonthlyResourceAllocation, Integer>{

}
