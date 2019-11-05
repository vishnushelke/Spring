/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception while updating note scenerio
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.exception.userexception;

public class UpdateNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public UpdateNoteExcepion(String message)
	{
		super(message);
		
	}
}
