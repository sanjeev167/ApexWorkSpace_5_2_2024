package com.nus.base.ctrl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.nus.api.struct.ApiHead;
import com.nus.api.struct.ApiRequest;
import com.nus.api.struct.ApiResponse;
import com.nus.base.service.UserLoginBaseService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 09-Jan-2025<br>
 * @Time: 7:31:43 am<br>
 * @Objective: <br>
 */
public class ApexBaseCtrl extends UserLoginBaseService{
		
	protected String dateFormatUsed = "dd-MM-yyyy HH:mm:ss";
	protected ApiRequest apiReq = null;
	protected String contentTypeRsp;
	protected Integer httpStatus;
	protected String apiResMsg;
	protected String errorMsg;
	protected Object resObj = null;
	protected ApiResponse apiResponse = new ApiResponse();
	protected String gridNodeCode =null;
	protected String apiUser =null;	
	
	protected ApiRequest makeApiMetaData() {
		
		Authentication auth = null;		 
		try {			
		auth = SecurityContextHolder.getContext().getAuthentication();	
		
		apiUser = auth.getName();			
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String contentTypeRcvd=request.getContentType();		
		String methodName=request.getMethod();
		String apiUrl=request.getRequestURI();
		// do something
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormatUsed);
		LocalDateTime now = LocalDateTime.now();
		String apiCallReceived = dtf.format(now);
		String apiCallResponded =  dtf.format(LocalDateTime.now());			
	    apiReq = new ApiRequest( apiUrl,
	    		                methodName, 	    		                
	    		                contentTypeRcvd, 
	    		                contentTypeRsp, 
	    		                apiCallReceived,
	    		                apiCallResponded,
	    		                apiUser);
	    
		}catch(Exception ex) {ex.printStackTrace();}
		return apiReq;
		
	}// End of makeApiMetaData
	
	protected ApiRequest makeApiMetaDataWhileLogin(String userName) {
			 
		try {				
		apiUser = userName;					
		ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = servletRequestAttributes.getRequest();
		String contentTypeRcvd=request.getContentType();		
		String methodName=request.getMethod();
		String apiUrl=request.getRequestURI();
		// do something
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dateFormatUsed);
		LocalDateTime now = LocalDateTime.now();
		String apiCallReceived = dtf.format(now);
		String apiCallResponded =  dtf.format(LocalDateTime.now());		
		
	    apiReq = new ApiRequest( apiUrl,
	    		                methodName, 	    		                
	    		                contentTypeRcvd, 
	    		                contentTypeRsp, 
	    		                apiCallReceived,
	    		                apiCallResponded);
	    
	   
		}catch(Exception ex) {ex.printStackTrace();}
		return apiReq;
		
	}// End of makeApiMetaData
	
	protected ApiResponse makeSuccessResponse(Object object, ApiRequest apiReq) {
	   
		ApiResponse apiResponse = new ApiResponse();			
		ApiHead head = new ApiHead(LocalDateTime.now(), HttpStatus.OK, "Successful","200");			
		apiResponse.setMeta(apiReq);
		apiResponse.setBody(object); 
		apiResponse.setHead(head);
		
		return apiResponse;
	}

}// End of ApiBaseCtrl

