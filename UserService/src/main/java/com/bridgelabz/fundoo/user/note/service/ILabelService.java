/******************************************************************************
*  Purpose: This is an interface which has all methods which we want to implement
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.service;

import com.bridgelabz.fundoo.user.note.dto.LabelDto;
import com.bridgelabz.fundoo.user.response.Response;

public interface ILabelService {
	
	/**
	 * purpose: This method is used for creating a label in a database of a
	 * particular user.
	 * 
	 * @param addLabelDto Data Transfer Object sent while creating a label
	 * @return Response according to the result
	 */
	public Response createLabel(LabelDto addLabelDto,String tokenUserId);
	
	/**
	 * purpose: This method is used for displays label in a database of a particular
	 * user.
	 * 
	 * @param tokenLabelId of the user whose label to be displayed
	 * @return Response according to the result
	 */
	public Response getLabel(String tokenUserId);
	
	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	public Response updateLabel(int labelId,LabelDto addLabelDto,String tokenUserId);
	
	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	public Response deleteLabel(int labelId,String tokenUserId);
	
	/**
	 * purpose: this method is used for getting notes of particular user present in a label
	 * @param labelId id of label
	 * @param tokenUserId token of user
	 * @return response according to result
	 */
	public Response getNotesOFLabel(int labelId,String tokenUserId);

}
