package com.nus.sec.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.ApexUserGroup;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 13-Jan-2025<br>
 * @Time: 9:03:33 pm<br>
 * @Objective: <br>
 */
@Repository
public interface ApexUserGroupRepository extends JpaRepository<ApexUserGroup, Integer>{

}//End of ApiUserGroupRepository
