package com.nus.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexGroup;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 8:58:00 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ApexGroupRepository extends JpaRepository<ApexGroup, Integer>{

}//End of ApexGroupRepository

