package com.bridgelabz.fundoo.note.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.note.dto.AddLabelDto;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.repository.ILabelRepository;
import com.bridgelabz.fundoo.note.utility.StaticReference;
import com.bridgelabz.fundoo.note.model.Label;

public class ImplLabelService implements ILabelService {
	
	@Autowired
	ILabelRepository repository;
	
	@Autowired
	ModelMapper mapper;

	@Override
	public Response createLabel(AddLabelDto addLabelDto) {
		// TODO Auto-generated method stub
		Label label = mapper.map(addLabelDto, Label.class);
		repository.save(label);
		return new Response(200, StaticReference.LABEL_SAVE_SUCCESS, label);
	}

	@Override
	public Response getLabel(String name) {
		// TODO Auto-generated method stub
		Label label = repository.findAll().stream().findAny().filter(i->i.getName().equals(name)).orElse(null);
		return new Response(200, StaticReference.LABEL_READ_SUCCES, label);
	}

	@Override
	public Response updateLabel(String name,String updationName) {
		// TODO Auto-generated method stub
		Label label = repository.findAll().stream().findAny().filter(i->i.getName().equals(name)).orElse(null);
		label.setName(updationName);
		repository.save(label);
		return new Response(200, StaticReference.LABEL_UPDATE_SUCCESS, label);
	}

	@Override
	public Response deleteLabel(String name) {
		// TODO Auto-generated method stub
		repository.delete(repository.findAll().stream().findAny().filter(i->i.getName().equals(name)).get());
		return new Response(200, StaticReference.LABEL_DELETE_SUCCESS, true);
	}

	
}
