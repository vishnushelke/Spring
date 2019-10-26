package com.bridgelabz.fundoo.note.dto;

import java.util.Date;

public class UpdateNoteDto {
	
	private int userId;
	private int noteId;
	private String title;
	private String text;
	private boolean trash;
	private boolean pin;
	private boolean archive;
	private Date noteCreationDate;
	private Date noteUpdationDate;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public boolean isTrash() {
		return trash;
	}
	public void setTrash(boolean trash) {
		this.trash = trash;
	}
	public boolean isPin() {
		return pin;
	}
	public void setPin(boolean pin) {
		this.pin = pin;
	}
	public boolean isArchive() {
		return archive;
	}
	public void setArchive(boolean archive) {
		this.archive = archive;
	}
	public Date getNoteCreationDate() {
		return noteCreationDate;
	}
	public void setNoteCreationDate(Date noteCreationDate) {
		this.noteCreationDate = noteCreationDate;
	}
	public Date getNoteUpdationDate() {
		return noteUpdationDate;
	}
	public void setNoteUpdationDate(Date noteUpdationDate) {
		this.noteUpdationDate = noteUpdationDate;
	}
	
}
