package com.bridgelabz.fundoo.user.exception.custom;

public class NotActiveException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose: this method will create an exception for login
	 * @param message
	 */
	public NotActiveException(String message)
	{
		super(message);
	}

}
