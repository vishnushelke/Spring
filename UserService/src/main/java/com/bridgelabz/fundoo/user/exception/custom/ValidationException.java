package com.bridgelabz.fundoo.user.exception.custom;

public class ValidationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose this method will create an exception for Validation password
	 * @param message
	 */
	public ValidationException(String message)
	{
		super(message);
	}

}
