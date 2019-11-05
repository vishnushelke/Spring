/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception while Registration scenerio
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.exception.custom;

public class RegistrationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose: this method will create an exception for Registration
	 * @param message
	 */
	public RegistrationException(String message)
	{
		super(message);
	}

}
