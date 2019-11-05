/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception while logging scenerio
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
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
