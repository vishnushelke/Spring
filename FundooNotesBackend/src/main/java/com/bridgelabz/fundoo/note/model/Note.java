package com.bridgelabz.fundoo.note.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "note")
public class Note {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "node_id")
	private int noteId;
	@Column(name = "user_id")
	private int userId;
	private String title;
	private String text;
	private boolean trash;
	private boolean pin;
	private boolean archive;
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date noteCreationDate;
	@Temporal(TemporalType.TIMESTAMP)
	@UpdateTimestamp
	private Date noteUpdationDate;

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

	public boolean getTrash() {
		return trash;
	}

	public void setNoteId(int noteId) {
		this.noteId = noteId;
	}

	public void setNoteCreationDate(Date noteCreationDate) {
		this.noteCreationDate = noteCreationDate;
	}

	public void setNoteUpdationDate(Date noteUpdationDate) {
		this.noteUpdationDate = noteUpdationDate;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	public boolean getPin() {
		return pin;
	}

	public void setPin(boolean pin) {
		this.pin = pin;
	}

	public boolean getArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public int getNoteId() {
		return noteId;
	}

	public Date getNoteCreationDate() {
		return noteCreationDate;
	}

	public Date getNoteUpdationDate() {
		return noteUpdationDate;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Note [noteId=" + noteId + ", title=" + title + ", text=" + text + ", trash=" + trash + ", pin=" + pin
				+ ", archive=" + archive + ", noteCreationDate=" + noteCreationDate + ", noteUpdationDate="
				+ noteUpdationDate + "]";
	}

}
