package com.bridgelabz.fundoo.note.dto;

public class DeleteNoteDto {

	private int userId;
	private int noteId;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getNoteId() {
		return noteId;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

}
