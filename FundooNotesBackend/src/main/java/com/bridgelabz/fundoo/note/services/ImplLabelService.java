package com.bridgelabz.fundoo.note.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.dto.LabelDto;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.repository.ILabelRepository;
import com.bridgelabz.fundoo.note.userexception.GetLabelExcepion;
import com.bridgelabz.fundoo.note.utility.StaticReference;
import com.bridgelabz.fundoo.note.utility.TokenUtility;

import com.bridgelabz.fundoo.note.model.Label;

@Service
public class ImplLabelService implements ILabelService {

	@Autowired
	private ILabelRepository repository;

	@Autowired
	private TokenUtility utility;

	@Autowired
	private ModelMapper mapper;

	/**
	 * purpose: This method is used for creating a label in a database of a
	 * particular user.
	 * 
	 * @param addLabelDto Data Transfer Object sent while creating a label
	 * @return Response according to the result
	 */
	@Override
	public Response createLabel(LabelDto addLabelDto) {
		Label label = mapper.map(addLabelDto, Label.class);
		repository.save(label);
		String tokenLabelId = utility.createToken(label.getLabelId());
		return new Response(200, StaticReference.LABEL_SAVE_SUCCESS, tokenLabelId);
	}

	/**
	 * purpose: This method is used for displays label in a database of a particular
	 * user.
	 * 
	 * @param tokenLabelId of the user whose label to be displayed
	 * @return Response according to the result
	 */

	@Override
	public Response getLabel(String tokenLabelId) {
		int labelId = utility.getIdFromToken(tokenLabelId);
		if (repository.findById(labelId) == null)
			throw new GetLabelExcepion(StaticReference.LABEL_NOT_FOUND);
		Label label = repository.findAll().stream().findAny().filter(i -> i.getLabelId() == labelId).orElse(null);
		return new Response(200, StaticReference.LABEL_READ_SUCCES, label);
	}

	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	@Override
	public Response updateLabel(String tokenLabelId, LabelDto labelDto) {
		int labelId = utility.getIdFromToken(tokenLabelId);
		if (repository.findById(labelId) == null)
			throw new GetLabelExcepion(StaticReference.LABEL_NOT_FOUND);
		Label label = repository.findAll().stream().findAny().filter(i -> i.getLabelId() == labelId).orElse(null);
		label.setName(labelDto.getName());
		repository.save(label);
		return new Response(200, StaticReference.LABEL_UPDATE_SUCCESS, label);
	}

	/**
	 * purpose: This method is used for deleting a particular label in a database.
	 * 
	 * @param tokenLabelId of the label which is to be deleted
	 * @return Response according to the result
	 */
	@Override
	public Response deleteLabel(String tokenLabelId) {
		int labelId = utility.getIdFromToken(tokenLabelId);
		if (repository.findById(labelId) == null)
			throw new GetLabelExcepion(StaticReference.LABEL_NOT_FOUND);
		repository.delete(repository.findAll().stream().findAny().filter(i -> i.getLabelId() == labelId).get());
		return new Response(200, StaticReference.LABEL_DELETE_SUCCESS, true);
	}

}
