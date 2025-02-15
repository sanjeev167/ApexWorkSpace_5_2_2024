package com.nus.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @Author:SanjeevKumar<br>
 * @Date:20-Jan-2025<br>
 * @Time:4:56:11 am<br>
 * @Objective: <br>
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class MalformedJwtException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public MalformedJwtException(String message) {
		super(message);		
	}
}
