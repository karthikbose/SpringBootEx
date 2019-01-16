package com.karthikbose.springboot.exception;

import java.util.Date;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
		ExceptionResponse er = new ExceptionResponse(new Date(), ex.getMessage());
		return new ResponseEntity<Object>(er, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
//		String strErrMsg =  ex.getBindingResult().getAllErrors().stream().map(oErr -> oErr.getDefaultMessage()).reduce((allMsg, msg) -> { return allMsg + msg; }).get();
//		String strErrMsg =  ex.getBindingResult().getAllErrors().stream().map(oErr -> oErr.getDefaultMessage()).reduce(",", String::concat);
		String strErrMsg =  ex.getBindingResult().getAllErrors().stream().map(oErr -> ((FieldError)oErr).getField()+"->"+oErr.getDefaultMessage()).collect(Collectors.joining(","));
		
		strErrMsg = "Count: "+ex.getBindingResult().getErrorCount()+", Message: "+ strErrMsg;
		ExceptionResponse er = new ExceptionResponse(new Date(), strErrMsg);
		return new ResponseEntity<Object>(er, HttpStatus.BAD_REQUEST);
	}
}
