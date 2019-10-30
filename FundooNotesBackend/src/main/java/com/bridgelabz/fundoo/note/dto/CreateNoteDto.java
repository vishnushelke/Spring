package com.bridgelabz.fundoo.note.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteDto {

	private int noteId;
	private int userId;
	private String title;
	private String text;
	private boolean trash;
	private boolean pin;
	private boolean archive;
	private Date noteCreationDate;
	private Date noteUpdationDate;

}
