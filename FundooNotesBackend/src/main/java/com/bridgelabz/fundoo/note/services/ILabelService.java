package com.bridgelabz.fundoo.note.services;

import com.bridgelabz.fundoo.note.dto.AddLabelDto;
import com.bridgelabz.fundoo.note.model.Response;

public interface ILabelService {
	
	public Response createLabel(AddLabelDto addLabelDto);
	
	public Response getLabel(String name);
	
	public Response updateLabel(String name,String updationName);
	
	public Response deleteLabel(String name);

}
