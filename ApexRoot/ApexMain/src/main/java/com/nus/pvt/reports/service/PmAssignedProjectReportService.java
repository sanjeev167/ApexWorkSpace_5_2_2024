package com.nus.pvt.reports.service;

import java.util.Date;

import com.nus.pvt.reports.model.PmProjectReportModel;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Feb-2025<br>
 * @Time: 4:01:01 pm<br>
 * @Objective: <br>
 */
public interface PmAssignedProjectReportService {

	public PmProjectReportModel getPmReports(Date fFileUploadDate,Date tFileUploadDate) throws Exception;
}
