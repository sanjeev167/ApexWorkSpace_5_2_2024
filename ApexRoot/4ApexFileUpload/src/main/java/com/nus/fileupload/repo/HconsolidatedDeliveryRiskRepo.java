package com.nus.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.HconsolidatedDeliveryRisk;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 02-Feb-2025<br>
 * @Time: 1:02:53 am<br>
 * @Objective: <br>
 */
@Repository
public interface HconsolidatedDeliveryRiskRepo extends JpaRepository<HconsolidatedDeliveryRisk, Integer>{

}
