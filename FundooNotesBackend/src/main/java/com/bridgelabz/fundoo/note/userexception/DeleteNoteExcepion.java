package com.bridgelabz.fundoo.note.userexception;

public class DeleteNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public DeleteNoteExcepion(String message)
	{
		super(message);
		
	}
}
