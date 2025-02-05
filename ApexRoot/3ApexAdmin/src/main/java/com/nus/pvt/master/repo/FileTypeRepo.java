package com.nus.pvt.master.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nus.fileupload.entities.FileType;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 30-Jan-2025<br>
 * @Time: 12:31:30 pm<br>
 * @Objective: <br>
 */
@Repository
public interface FileTypeRepo extends JpaRepository<FileType, Integer>{

	List<FileType> findAllByActiveC(String activeC);

}
