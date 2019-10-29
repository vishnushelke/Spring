package com.bridgelabz.fundoo.note.userexception;

public class GetNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public GetNoteExcepion(String message)
	{
		super(message);
		
	}
}
