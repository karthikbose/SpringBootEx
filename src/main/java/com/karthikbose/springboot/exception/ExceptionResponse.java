package com.karthikbose.springboot.exception;

import java.util.Date;

import lombok.Data;

@Data
public class ExceptionResponse {

	private Date date;
	private String errorMessage;
	
	public ExceptionResponse(Date date, String errorMessage) {
		super();
		this.date = date;
		this.errorMessage = errorMessage;
	}
	
	
}
