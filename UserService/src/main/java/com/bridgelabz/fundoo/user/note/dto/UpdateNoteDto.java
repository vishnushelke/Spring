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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateNoteDto {
	
	private String title;
	private String text;
	
}
