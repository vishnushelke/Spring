package com.bridgelabz.fundoo.note.userexception;

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
