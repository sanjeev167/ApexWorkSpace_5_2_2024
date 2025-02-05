package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.HcustomerSatisfaction;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 02-Feb-2025<br>
 * @Time: 12:18:50 am<br>
 * @Objective: <br>
 */
@Repository
public interface HcustomerSatisfactionRepo extends JpaRepository<HcustomerSatisfaction, Integer>{

}
