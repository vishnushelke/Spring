/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception while forgetpassword scenerio
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.exception.custom;

public class ForgetPasswordException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * purpose: this method will create an exception for forget password
	 * @param message
	 */
	public ForgetPasswordException(String message)
	{
		super(message);
	}

}
