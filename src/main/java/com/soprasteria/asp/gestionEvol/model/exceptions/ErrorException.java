package com.soprasteria.asp.gestionEvol.model.exceptions;

public class ErrorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5790739391576344725L;
	private String name;
	private String error_message;
	private int error_code;
	
	
	public ErrorException( String message ) {

        super( message );

    }

    public ErrorException( String message, Throwable cause ) {

        super( message, cause );

    }

    public ErrorException( Throwable cause ) {

        super( cause );

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


	public void setError_code(String error_code) {
		this.error_code = Integer.parseInt(error_code);
	}

}
