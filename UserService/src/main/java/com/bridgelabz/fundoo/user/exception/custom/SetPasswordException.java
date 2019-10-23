package com.bridgelabz.fundoo.user.exception.custom;

public class SetPasswordException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose this method will create an exception for Set password
	 * @param message
	 */
	public SetPasswordException(String message)
	{
		super(message);
	}

}
