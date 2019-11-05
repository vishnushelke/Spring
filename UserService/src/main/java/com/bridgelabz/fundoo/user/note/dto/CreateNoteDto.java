/******************************************************************************
*  Purpose: This class is a DTO class which maps the values from response which 
*  			is caught by @requestbody
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   30-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.dto;

import java.util.Date;
import java.util.List;

import com.bridgelabz.fundoo.user.note.model.Label;

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
