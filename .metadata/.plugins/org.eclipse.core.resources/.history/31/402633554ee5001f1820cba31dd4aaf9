/**
 * 
 */
package com.nus.fileupload.loader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.nus.fileupload.entities.FileType;
import com.nus.pvt.master.entities.ProjectCodeMstr;
import com.nus.pvt.master.repo.FileTypeRepo;
import com.nus.pvt.master.repo.ProjectCodeMstrRepo;

/**
 * @Author:SanjeevKumar<br>
 * @Date:25-Jan-2025<br>
 * @Time:13:44:48 pm<br>
 * @Objective: <br>
 */
@Component
public class DataLoader implements ApplicationRunner {

	HashMap<String, ProjectCodeMstr> projectCodeMstrMap = new HashMap<String, ProjectCodeMstr>();
	private final ProjectCodeMstrRepo projectCodeMstrRepo;
	
	
	HashMap<Integer, FileType> fileTypeMap = new HashMap<Integer, FileType>();
	private final FileTypeRepo fileTypeRepo;

	public DataLoader(FileTypeRepo fileTypeRepo,ProjectCodeMstrRepo projectCodeMstrRepo) {
		this.fileTypeRepo = fileTypeRepo;
		this.projectCodeMstrRepo = projectCodeMstrRepo;
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		List<ProjectCodeMstr> projectCodeMstrList = projectCodeMstrRepo.findAllByActiveC("Y");
		Iterator<ProjectCodeMstr> projectCodeMstrIterator = projectCodeMstrList.iterator();
		
		while (projectCodeMstrIterator.hasNext()) {
			ProjectCodeMstr projectCodeMstr = projectCodeMstrIterator.next();			
			projectCodeMstrMap.put(projectCodeMstr.getProjectCode(), projectCodeMstr);
		}
		
		List<FileType> fileTypeList = fileTypeRepo.findAllByActiveC("Y");
		Iterator<FileType> fileTypeIterator = fileTypeList.iterator();
		
		while (fileTypeIterator.hasNext()) {
			FileType fileType = fileTypeIterator.next();			
			fileTypeMap.put(fileType.getId(), fileType);
		}		
		
	}

	/**
	 * @return the projectCodeMstrMap
	 */
	public HashMap<String, ProjectCodeMstr> getProjectCodeMstrMap() {
		return projectCodeMstrMap;
	}

	/**
	 * @param projectCodeMstrMap the projectCodeMstrMap to set
	 */
	public void setProjectCodeMstrMap(HashMap<String, ProjectCodeMstr> projectCodeMstrMap) {
		this.projectCodeMstrMap = projectCodeMstrMap;
	}

	/**
	 * @return the fileTypeMap
	 */
	public HashMap<Integer, FileType> getFileTypeMap() {
		return fileTypeMap;
	}

	/**
	 * @param fileTypeMap the fileTypeMap to set
	 */
	public void setFileTypeMap(HashMap<Integer, FileType> fileTypeMap) {
		this.fileTypeMap = fileTypeMap;
	}
	
	
}
