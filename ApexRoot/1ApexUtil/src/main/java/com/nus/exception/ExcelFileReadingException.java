package com.nus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 27-Jan-2025<br>
 * @Time: 7:51:41 pm<br>
 * @Objective: <br>
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExcelFileReadingException extends RuntimeException{	
	private static final long serialVersionUID = 1L;
	
	public ExcelFileReadingException(String message) {
	    super(String.format("Failed for [%s]: %s", message));
	  }
}
