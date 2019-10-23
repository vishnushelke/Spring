package com.bridgelabz.fundoo.user.exception.custom;

public class ForgetPasswordException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * purpose this method will create an exception for forget password
	 * @param message
	 */
	public ForgetPasswordException(String message)
	{
		super(message);
	}

}
