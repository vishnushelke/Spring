package com.bridgelabz.fundoo.note.services;

import com.bridgelabz.fundoo.note.dto.LabelDto;
import com.bridgelabz.fundoo.note.model.Response;

public interface ILabelService {
	
	/**
	 * purpose: This method is used for creating a label in a database of a
	 * particular user.
	 * 
	 * @param addLabelDto Data Transfer Object sent while creating a label
	 * @return Response according to the result
	 */
	public Response createLabel(LabelDto addLabelDto);
	
	/**
	 * purpose: This method is used for displays label in a database of a particular
	 * user.
	 * 
	 * @param tokenLabelId of the user whose label to be displayed
	 * @return Response according to the result
	 */
	public Response getLabel(String tokenLabelId);
	
	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	public Response updateLabel(String tokenLabelId,LabelDto addLabelDto);
	
	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	public Response deleteLabel(String tokenLabelId);

}
