package com.soprasteria.asp.gestionEvol.model;

import com.soprasteria.asp.gestionEvol.model.exceptions.ErrorException;

public class ResponseError {

	String name;
	String error_message;
	int error_code;
	
	public ResponseError(ErrorException error ) {
		this.name = error.getName();
		this.error_message = error.getError_message();
		this.error_code = error.getError_code();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getError_message() {
		return error_message;
	}

	public void setError_message(String error_message) {
		this.error_message = error_message;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
	
	
}
