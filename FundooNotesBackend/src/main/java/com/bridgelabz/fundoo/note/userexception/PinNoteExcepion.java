package com.bridgelabz.fundoo.note.userexception;

public class PinNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public PinNoteExcepion(String message)
	{
		super(message);
		
	}
}
