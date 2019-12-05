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

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.note.model.Label;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateNoteDto {
	private int userId;
	private String title;
	private String text;
	private boolean trash;
	private boolean pin;
	private boolean archive;
	private String reminder;
	private String colour;
	private List<Label> labels;
	private List<User> collabUsers;
	@Override
	public String toString() {
		return "CreateNoteDto [userId=" + userId + ", title=" + title + ", text=" + text + ", trash=" + trash + ", pin="
				+ pin + ", archive=" + archive + ", reminder=" + reminder + ", colour=" + colour + ", labels=" + labels
				+ ", collabUsers=" + collabUsers + "]";
	}
	
}
