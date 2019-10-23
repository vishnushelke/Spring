package com.bridgelabz.fundoo.user.exception.custom;

public class RegistrationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose this method will create an exception for Registration
	 * @param message
	 */
	public RegistrationException(String message)
	{
		super(message);
	}

}
