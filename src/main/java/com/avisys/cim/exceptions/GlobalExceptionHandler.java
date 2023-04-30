package com.avisys.cim.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

//Global Exception Handling when duplicate Mobile Number Insert
@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(DuplicateMobileNumberException.class)
	public ResponseEntity<ApiResponse> duplicateNumberExceptionHandler(DuplicateMobileNumberException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
}
