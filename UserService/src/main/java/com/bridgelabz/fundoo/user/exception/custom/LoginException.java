package com.bridgelabz.fundoo.user.exception.custom;

public class LoginException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose: this method will create an exception for login
	 * @param message
	 */
	public LoginException(String message)
	{
		super(message);
	}

}
