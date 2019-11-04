package com.bridgelabz.fundoo.user.exception.custom;

public class UserNotFoundException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose: this method will create an exception for login
	 * @param message
	 */
	public UserNotFoundException(String message)
	{
		super(message);
	}

}
