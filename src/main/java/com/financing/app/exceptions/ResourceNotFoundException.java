package com.financing.app.exceptions;



public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1086264340206390209L;
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
