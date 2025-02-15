package com.nus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author: SanjeevKumar<br>
 * @Date: 08-Jan-2025<br>
 * @Time: 9:52:12 pm<br>
 * @Objective: <br>
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InternalServerException(String message) {
		super(message);
	}
}//End of InternalServerException
