package com.bridgelabz.fundoo.note.userexception;

public class CreateNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public CreateNoteExcepion(String message)
	{
		super(message);
		
	}
}
