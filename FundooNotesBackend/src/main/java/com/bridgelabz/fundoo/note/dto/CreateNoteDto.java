package com.bridgelabz.fundoo.note.dto;

import java.util.Date;
import java.util.List;

import com.bridgelabz.fundoo.note.model.Label;

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
	private List<Label> labels;
}
