package com.nus.pvt.reports.service;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nus.base.service.UserLoginBaseService;
import com.nus.fileupload.entities.ConsolidatedDeliveryRisk;
import com.nus.fileupload.entities.CustomerSatisfaction;
import com.nus.fileupload.entities.ProjectMonthlyPlcc;
import com.nus.fileupload.entities.ProjectMonthlyResourceAllocation;
import com.nus.fileupload.entities.ProjectMonthlyResourceAttrition;
import com.nus.fileupload.entities.ProjectMonthlyResourceUtilization;
import com.nus.fileupload.entities.ProjectMonthlyRrrr;
import com.nus.fileupload.model.ResourceAllocationModel;
import com.nus.fileupload.repo.MonthlyResourceAllocationRepo;
import com.nus.fileupload.service.MonthlyAttritionService;
import com.nus.fileupload.service.MonthlyConsolidatedDeliveryRiskService;
import com.nus.fileupload.service.MonthlyCustomerSatisfactionService;
import com.nus.fileupload.service.MonthlyRRRService;
import com.nus.fileupload.service.MonthlyResourceAllocationService;
import com.nus.fileupload.service.MonthlyResourceUtilizationService;
import com.nus.fileupload.service.ProjectMonthlyPlccService;
import com.nus.pvt.admin.entities.AssignProjectToPm;
import com.nus.pvt.admin.repo.AssignProjectToPmRepo;
import com.nus.pvt.reports.model.PmProjectReportModel;
import com.nus.pvt.reports.model.ProjectDetails;
import com.nus.util.Utility;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Feb-2025<br>
 * @Time: 4:01:12 pm<br>
 * @Objective: <br>
 */

@Service
public class PmAssignedProjectReportServiceImpl extends UserLoginBaseService implements PmAssignedProjectReportService {

	@Autowired
	AssignProjectToPmRepo assignProjectToPmRepo;

	@Override
	public PmProjectReportModel getPmReports(Date fFileUploadDate,Date tFileUploadDate) throws Exception {
         
		PmProjectReportModel pMProjectReportModel = null;
		try {
			List<AssignProjectToPm> assignProjectToPmList = assignProjectToPmRepo.findByPm(getCurrentLoginUserId(),
					activeC);
			if (assignProjectToPmList.size() != 0) {
				pMProjectReportModel = new PmProjectReportModel();
				pMProjectReportModel.setPm(assignProjectToPmList.get(0).getApexUser3().getName());
				pMProjectReportModel
						.setEmpGrade(assignProjectToPmList.get(0).getApexUser3().getUserGrade().getUserGrade());
				pMProjectReportModel.setfMonthYr(Utility.getDateInMMYYYY(fFileUploadDate));
				pMProjectReportModel.settMonthYr(Utility.getDateInMMYYYY(tFileUploadDate));
				pMProjectReportModel.setReportFor(Utility.monthsBetweenDates(fFileUploadDate, tFileUploadDate) + " - Month");
			}
			ProjectDetails projectDetails;
			for (AssignProjectToPm assignProjectToPm : assignProjectToPmList) {
				projectDetails = new ProjectDetails();
				projectDetails.setAssignedOn(dateFormatter.format(assignProjectToPm.getAssignedOn()));

				if (assignProjectToPm.getReleasedOn() != null)
					projectDetails.setReleasedOn(dateFormatter.format(assignProjectToPm.getReleasedOn()));
				if (assignProjectToPm.getProjectClosedOn() != null)
					projectDetails.setClosedOn(dateFormatter.format(assignProjectToPm.getProjectClosedOn()));

				projectDetails.setClientAccount(assignProjectToPm.getClientAccountMstr().getClientAccount());
				projectDetails.setProjectVertical(assignProjectToPm.getProjectVerticalMstr().getVerticalName());
				projectDetails.setProjectCode(assignProjectToPm.getProjectCodeMstr().getProjectCode());
				projectDetails.setProjectName(assignProjectToPm.getProjectCodeMstr().getProjectName());
				projectDetails.setProjectType(assignProjectToPm.getProjectTypeMstr().getProjectType());

				prepareOtherProjectSpecificData(assignProjectToPm.getProjectCodeId(), projectDetails, fFileUploadDate,tFileUploadDate);

				pMProjectReportModel.getProjectDetailReportList().add(projectDetails);
			}
		} catch (Exception ex) {
			throw ex;
		}
		return pMProjectReportModel;
	}

	private void prepareOtherProjectSpecificData(int projectCodeId, ProjectDetails projectDetails,Date fFileUploadDate,Date tFileUploadDate) throws ParseException {
		preparePLCC(projectCodeId, projectDetails, fFileUploadDate,tFileUploadDate);		
		prepareUtilization(projectCodeId, projectDetails, fFileUploadDate, tFileUploadDate);		
		prepareAttrition(projectCodeId, projectDetails, fFileUploadDate, tFileUploadDate);
		prepareAllocation(projectCodeId, projectDetails, fFileUploadDate, tFileUploadDate);
		prepareRRRR(projectCodeId, projectDetails, fFileUploadDate, tFileUploadDate);		
		prepareCustomerSatisfactionScore(projectCodeId, projectDetails, fFileUploadDate, tFileUploadDate);
		prepareProjectRiskScore(projectCodeId, projectDetails, fFileUploadDate, tFileUploadDate);
	}

	@Autowired
	ProjectMonthlyPlccService projectMonthlyPlccService;
	private void preparePLCC(int projectCodeId, ProjectDetails projectDetails, Date fFileUploadDate,Date tFileUploadDate) {
		
		List<ProjectMonthlyPlcc> monthlyPlccBetweenMonthsList = projectMonthlyPlccService
				.getMonthlyPlccBetweenMonths(projectCodeId, fFileUploadDate,tFileUploadDate);

		// Full time employee [FTE] will come from PLcc using H-column.
		double fTeForYTD = 0;
		double marginAmountForYTD = 0;
		double totalRevenueAmountForYTD = 0;

		for (ProjectMonthlyPlcc projectMonthlyPlcc : monthlyPlccBetweenMonthsList) {
			fTeForYTD = fTeForYTD + projectMonthlyPlcc.getFte();
			marginAmountForYTD = marginAmountForYTD + projectMonthlyPlcc.getMarginAmount();
			totalRevenueAmountForYTD = totalRevenueAmountForYTD + projectMonthlyPlcc.getTotalRevenueAmount();
			
			//if(projectMonthlyPlcc.getProjectCodeMstr().getProjectCode().equalsIgnoreCase("CC400037"))
			//System.out.println("fTeForYTD -> "+fTeForYTD+" marginAmountForYTD=> "+marginAmountForYTD +" totalRevenueAmountForYTD => "+totalRevenueAmountForYTD);
		}
		
		projectDetails.setyTDFte(Utility.roundOffDoubleUpto2Places(fTeForYTD));
		projectDetails.setyTDRevenue(Utility.roundOffDoubleUpto2Places(totalRevenueAmountForYTD));
		projectDetails.setRevenuePerFTE(Utility.roundOffDoubleUpto2Places(totalRevenueAmountForYTD/fTeForYTD));// checked
		
		projectDetails.setyTDPmPercent(Utility.roundOffDoubleUpto2Places((marginAmountForYTD / totalRevenueAmountForYTD)*100) + " %");		
		
		projectDetails.setCostPerFTE(Utility.roundOffDoubleUpto2Places((totalRevenueAmountForYTD - marginAmountForYTD)/fTeForYTD));
	}

	@Autowired
	MonthlyResourceUtilizationService monthlyResourceUtilizationService;
	private void prepareUtilization(Integer projectCodeId, ProjectDetails projectDetails, Date fFileUploadDate,Date tFileUploadDate) {
		List<ProjectMonthlyResourceUtilization> monthlyResourceUtilizationList = monthlyResourceUtilizationService
				.getMonthlyResourceUtilizationBetweenMonths(projectCodeId, fFileUploadDate,tFileUploadDate);
//YTD Utilization percentage
		double billedDaysSumForYTD = 0;
		double availableDaysSumForYTD = 0;
		for (ProjectMonthlyResourceUtilization monthlyResourceUtilizationForYTD : monthlyResourceUtilizationList) {
			billedDaysSumForYTD = billedDaysSumForYTD + monthlyResourceUtilizationForYTD.getBilledDays();
			availableDaysSumForYTD = availableDaysSumForYTD + monthlyResourceUtilizationForYTD.getAvailableDays();
		}
		
		projectDetails.setyTDUtilPercent(Utility.roundOffDoubleUpto2Places((billedDaysSumForYTD / availableDaysSumForYTD) * 100) + " %");

//Utilization for 2 month back from given toMonth
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(tFileUploadDate);
		// Subtract 2 months from current date
        calendar.add(Calendar.MONTH, -2);
        Date twoMonthBackDate=calendar.getTime();
		
		// Reinitialize for cleaning the staled data
		billedDaysSumForYTD = 0;
		availableDaysSumForYTD = 0;
		monthlyResourceUtilizationList = monthlyResourceUtilizationService.getMonthlyResourceUtilizationBetweenMonths(
				projectCodeId, fFileUploadDate, twoMonthBackDate);

		for (ProjectMonthlyResourceUtilization monthlyResourceUtilizationForYTD : monthlyResourceUtilizationList) {
			billedDaysSumForYTD = billedDaysSumForYTD + monthlyResourceUtilizationForYTD.getBilledDays();
			availableDaysSumForYTD = availableDaysSumForYTD + monthlyResourceUtilizationForYTD.getAvailableDays();
		}
		
		projectDetails.setTwoMonthBackMonthUtilPercent(Utility.roundOffDoubleUpto2Places((billedDaysSumForYTD / availableDaysSumForYTD) * 100) + " % "
				+ Utility.getMonthName(calendar.MONTH));

		
	}

	@Autowired
	MonthlyAttritionService monthlyAttritionService;
	private void prepareAttrition(Integer projectCodeId, ProjectDetails projectDetails,Date fFileUploadDate,Date tFileUploadDate) {
		// YTD Attrition calculation
		double ytd_attrition_percent = calculateYTDAttritionPercent(projectCodeId, fFileUploadDate,tFileUploadDate);
		projectDetails.setyTDAttritionPercent(ytd_attrition_percent + " %");
		// Attrition for current month. [total_exit_count/ Total_resource_count] for current month
		Date currentMonthYearDate = tFileUploadDate;//Current mont will be considered as to date
		
		double currentMonthAttritionPercent = currentMonthAttritionPercent(projectCodeId, currentMonthYearDate);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(currentMonthYearDate);
		projectDetails.setCurrentAttritionPercent(
				currentMonthAttritionPercent + " % in " + Utility.getMonthName(calendar.MONTH));
	}// End of prepareAttrition
	@Autowired
	MonthlyResourceAllocationService monthlyResourceAllocationService;
	private double calculateYTDAttritionPercent(Integer projectCodeId, Date fFileUploadDate,Date tFileUploadDate) {
		List<ProjectMonthlyResourceAttrition> projectMonthlyResourceAttritionList = monthlyAttritionService
				.getMonthlyResourceAttritionDataBetweenMonths(projectCodeId, fFileUploadDate,tFileUploadDate);
		int total_exit_count = projectMonthlyResourceAttritionList.size();
		
		List<ResourceAllocationModel> projectMonthlyResourceAllocationList = monthlyResourceAllocationService
				.getMonthlyDistinctResourceAllocationBetweenMonths(projectCodeId, fFileUploadDate,tFileUploadDate);
		int totalResourceAllocationCount = projectMonthlyResourceAllocationList.size();		
		double ytd_attrition_percent = 0;
		if(totalResourceAllocationCount!=0)
		 ytd_attrition_percent = (total_exit_count / totalResourceAllocationCount) * 100;
		return ytd_attrition_percent;
	}

	
	private double currentMonthAttritionPercent(Integer projectCodeId, Date currentMonthYearDate) {

		List<ProjectMonthlyResourceAttrition> monthlyResourceAttritionList = monthlyAttritionService
				.getAMonthSpecificAttrition(projectCodeId, currentMonthYearDate);

		List<ProjectMonthlyResourceAllocation> resourceAllocationForCurrentLMonthist = monthlyResourceAllocationService
				.getDistinctResourceAllocationForCurrentMonth(projectCodeId, currentMonthYearDate);

		int totalResourceAllocationCount = resourceAllocationForCurrentLMonthist.size();
		int total_exit_count = monthlyResourceAttritionList.size();
		
		double currentMonthAttritionPercent = 0;
		if(totalResourceAllocationCount>0)
		 currentMonthAttritionPercent = (total_exit_count / totalResourceAllocationCount) * 100;	
		
		return currentMonthAttritionPercent;
	}

	// Here,Expected Increase P/M column data will be picked for RRRR [Potential
	// FTE]. It will be count of non-zero data of [Expected Increase P/M] for a project.
	// RRRR (Potential Rev/ Month) => Sum of all data [Expected Increase P/M] available for a month
	@Autowired
	MonthlyRRRService monthlyRRRService;
	private void prepareRRRR(int projectCodeId, ProjectDetails projectDetails,Date fFileUploadDate,Date tFileUploadDate) {
		Integer expectedIncreasePMCount = monthlyRRRService.getExpectedIncreasePMCountFromRRRR(projectCodeId,fFileUploadDate,tFileUploadDate);
		projectDetails.setRrrrPotentialFTE(expectedIncreasePMCount);
		List<ProjectMonthlyRrrr>rRRRPotentialRevenuePerMonthList = monthlyRRRService.getRRRRPotentialRevenuePerMonth(projectCodeId, fFileUploadDate,tFileUploadDate);
		double rRRRPotentialRevenuePerMonth = 0;
		for( ProjectMonthlyRrrr projectMonthlyRrrr:rRRRPotentialRevenuePerMonthList) {
			rRRRPotentialRevenuePerMonth = rRRRPotentialRevenuePerMonth+projectMonthlyRrrr.getExpectedRate();
		}
		projectDetails.setRrrrPotentialRevenuePerMonth(rRRRPotentialRevenuePerMonth);		
	}

	// Here,
	@Autowired
	MonthlyResourceAllocationRepo monthlyResourceAllocationRepo;
	private void prepareAllocation(int projectCodeId, ProjectDetails projectDetails, Date fFileUploadDate,Date tFileUploadDate) {
		List<ProjectMonthlyResourceAllocation> projectMonthlyResourceAllocationList = monthlyResourceAllocationRepo.getResourceAllocationForSpan_1AndSpan_2Calculation(projectCodeId, fFileUploadDate,tFileUploadDate);
		double SumOfAllocationPercentFor_EandS = 0;double SumOfAllocationPercentFor_M = 0;
		double SumOfAllocationPercentFor_L = 0;
		
		for( ProjectMonthlyResourceAllocation projectMonthlyResourceAllocation:projectMonthlyResourceAllocationList) {
			if(projectMonthlyResourceAllocation.getEmpGrade().startsWith("E") || projectMonthlyResourceAllocation.getEmpGrade().startsWith("S")) {
				SumOfAllocationPercentFor_EandS = SumOfAllocationPercentFor_EandS+projectMonthlyResourceAllocation.getAllocationPercentage();
			}
			if(projectMonthlyResourceAllocation.getEmpGrade().startsWith("M")) {
				SumOfAllocationPercentFor_M = SumOfAllocationPercentFor_M+projectMonthlyResourceAllocation.getAllocationPercentage();
			}
			if(projectMonthlyResourceAllocation.getEmpGrade().startsWith("L")) {
				SumOfAllocationPercentFor_L = SumOfAllocationPercentFor_L+projectMonthlyResourceAllocation.getAllocationPercentage();
			}
		}//End of for loop
		projectDetails.setSpan_1(((int)SumOfAllocationPercentFor_EandS + "")+":"+((int)SumOfAllocationPercentFor_M+""));
		projectDetails.setSpan_2(((int)SumOfAllocationPercentFor_M + "")+":"+((int)SumOfAllocationPercentFor_L+""));
		
		//X= SumFor_E&S / (SumFor_M+SumFor_L) this will go under Span RAG	
		int spanRagCalculation = 0;
		if((SumOfAllocationPercentFor_M + SumOfAllocationPercentFor_L)>0)
		 spanRagCalculation = (int)(SumOfAllocationPercentFor_EandS/
				                      (SumOfAllocationPercentFor_M + SumOfAllocationPercentFor_L));
		
		projectDetails.setSpanRAGCalc(spanRagCalculation);
		if(spanRagCalculation>10)
			projectDetails.setSpanRagColourCode("Green");
		else
			projectDetails.setSpanRagColourCode("Red");		
	}
	
	@Autowired
	MonthlyConsolidatedDeliveryRiskService monthlyConsolidatedDeliveryRiskService;
	private void prepareProjectRiskScore(int projectCodeId, ProjectDetails projectDetails,Date fFileUploadDate,Date tFileUploadDate) {
		List<ConsolidatedDeliveryRisk> consolidatedDeliveryRiskList =  monthlyConsolidatedDeliveryRiskService.getMonthlyConsolidatedDeliveryRiskBetweenMonths(projectCodeId,fFileUploadDate,tFileUploadDate);
		//Here, there is a possibility of get multiple records. So, we will convert them as comma separated.
		String customerDeliveryRiskScore = "_";
		for(ConsolidatedDeliveryRisk consolidatedDeliveryRisk:consolidatedDeliveryRiskList) {
			if(consolidatedDeliveryRisk.getRiskPriorityNumber()==null)
			    ;//Do nothing
			else
				customerDeliveryRiskScore = customerDeliveryRiskScore +" , "+consolidatedDeliveryRisk.getRiskPriorityNumber();
		}
		projectDetails.setProjectRiskScore(customerDeliveryRiskScore);		
	}

	@Autowired
	MonthlyCustomerSatisfactionService monthlyCustomerSatisfactionService;
	private void prepareCustomerSatisfactionScore(int projectCodeId, ProjectDetails projectDetails, Date fFileUploadDate,Date tFileUploadDate) {
		List<CustomerSatisfaction> csustomerSatisfactionList= monthlyCustomerSatisfactionService.getMonthlyCustomerSatisfactionBetweenMonths(projectCodeId, fFileUploadDate,tFileUploadDate);
		//Here, there is a possibility of get multiple records. So, we will convert them as comma separated
		String customerSatisfactionScore = "_";
		for(CustomerSatisfaction customerSatisfaction:csustomerSatisfactionList) {
			if(customerSatisfaction.getRating()==null)
				;//Do nothing
			else
				customerSatisfactionScore = customerSatisfactionScore +" , "+customerSatisfaction.getRating();
		}
		projectDetails.setProjectCsatScore(customerSatisfactionScore);
	}	
}
