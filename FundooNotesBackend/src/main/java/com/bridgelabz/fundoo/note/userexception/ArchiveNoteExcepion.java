package com.bridgelabz.fundoo.note.userexception;

public class ArchiveNoteExcepion extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * purpose: this method will create an exception
	 * @param message
	 */
	public ArchiveNoteExcepion(String message)
	{
		super(message);
		
	}
}
