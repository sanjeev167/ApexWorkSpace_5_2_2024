package com.nus.exception;
import org.springframework.http.ResponseEntity;

import com.nus.api.struct.ApiError;
/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Jan-2025<br>
 * @Time: 9:59:18 pm<br>
 * @Objective: <br>
 */
public class ResponseEntityBuilder {
	public static ResponseEntity<Object> build(ApiError apiError) {
	      return new ResponseEntity<>(apiError, apiError.getHead().getHttpStatus());
	}
}//End of ResponseEntityBuilder 
