/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception while setpassword scenerio
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.exception.custom;

public class SetPasswordException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose: this method will create an exception for Set password
	 * @param message
	 */
	public SetPasswordException(String message)
	{
		super(message);
	}

}
