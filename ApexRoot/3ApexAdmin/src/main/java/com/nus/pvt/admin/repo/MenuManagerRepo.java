package com.nus.pvt.admin.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nus.sec.entities.MenuManager;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 17-Jan-2025<br>
 * @Time: 4:54:26 pm<br>
 * @Objective: <br>
 */
@Repository
public interface MenuManagerRepo extends CrudRepository<MenuManager, Integer> {

}
