/******************************************************************************
*  Purpose: This is an exception class which can be used while throwing when
*  			we get an exception while sorting notes by updation date scenerio
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.exception.userexception;

public class SortByUpdationDateNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public SortByUpdationDateNoteExcepion(String message)
	{
		super(message);
		
	}
}
