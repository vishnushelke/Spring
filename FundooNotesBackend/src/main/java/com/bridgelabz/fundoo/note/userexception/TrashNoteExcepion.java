package com.bridgelabz.fundoo.note.userexception;

public class TrashNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public TrashNoteExcepion(String message)
	{
		super(message);
		
	}
}
