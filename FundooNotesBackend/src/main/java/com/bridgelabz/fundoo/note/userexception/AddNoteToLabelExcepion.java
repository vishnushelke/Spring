package com.bridgelabz.fundoo.note.userexception;

public class AddNoteToLabelExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public AddNoteToLabelExcepion(String message)
	{
		super(message);
		
	}
}
