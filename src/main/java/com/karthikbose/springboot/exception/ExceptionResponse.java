package com.karthikbose.springboot.exception;

import lombok.Data;

@Data
public class ExceptionResponse {

	private int errorCode;
	private String errorMessage;
}
