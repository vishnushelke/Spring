/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception when user has not done validations
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.exception.custom;

public class ValidationException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;
	
	/**
	 * purpose: this method will create an exception for Validation password
	 * @param message
	 */
	public ValidationException(String message)
	{
		super(message);
	}

}
