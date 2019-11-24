/******************************************************************************
*  Purpose: This class implements all methods of ILabelService interface
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.note.dto.LabelDto;
import com.bridgelabz.fundoo.user.note.exception.userexception.GetLabelExcepion;
import com.bridgelabz.fundoo.user.note.model.Label;
import com.bridgelabz.fundoo.user.note.model.Note;
import com.bridgelabz.fundoo.user.note.repository.ILabelRepository;
import com.bridgelabz.fundoo.user.note.utility.NoteMessageReference;
import com.bridgelabz.fundoo.user.note.utility.NoteTokenUtility;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;

@Service
public class ImplLabelService implements ILabelService {

	@Autowired
	private ILabelRepository repository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NoteTokenUtility utility;

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
	public Response createLabel(LabelDto addLabelDto, String tokenUserId) {
		System.out.println("hi mi aloy");
		if (repository.findAll().stream().anyMatch(i -> i.getName().equals(addLabelDto.getName()))) {
			return new Response(200, NoteMessageReference.LABEL_ALREADY_AVAILABLE, false);
		}
		if (!addLabelDto.getName().isBlank()) {
			Label label = mapper.map(addLabelDto, Label.class);
			label.setUserId(utility.getIdFromToken(tokenUserId));
			repository.save(label);
			String tokenLabelId = utility.createToken(label.getLabelId());
			return new Response(200, NoteMessageReference.LABEL_SAVE_SUCCESS, tokenLabelId);
		}
		return new Response(200, "label name can not be empty", false);
	}

	/**
	 * purpose: This method is used for displays label in a database of a particular
	 * user.
	 * 
	 * @param tokenLabelId of the user whose label to be displayed
	 * @return Response according to the result
	 */

	@Override
	public Response getLabel(String tokenUserId) {
		User user = userRepository.findById(utility.getIdFromToken(tokenUserId)).get();
		if (user == null) {
			throw new UserNotFoundException(NoteMessageReference.USER_NOT_FOUND);
		}
		if (repository.findById(user.getUId()) == null)
			throw new GetLabelExcepion(NoteMessageReference.LABEL_NOT_FOUND);
		Stream<Label> labels = repository.findAll().stream().filter(i -> i.getUserId() == user.getUId());
		ArrayList<Label> labelList = labels.collect(Collectors.toCollection(ArrayList::new));
		return new Response(200, NoteMessageReference.LABEL_READ_SUCCES, labelList);
	}

	/**
	 * purpose: This method is used for updating a particular label in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a
	 *                      label,tokenLabelId of a label
	 * @return Response according to the result
	 */
	@Override
	public Response updateLabel(int labelId, LabelDto labelDto, String tokenUserId) {

		if (repository.findById(labelId) == null)
			throw new GetLabelExcepion(NoteMessageReference.LABEL_NOT_FOUND);
		Label label = repository.findAll().stream().findAny().filter(i -> i.getLabelId() == labelId).orElse(null);
		label.setName(labelDto.getName());
		repository.save(label);
		return new Response(200, NoteMessageReference.LABEL_UPDATE_SUCCESS, label);
	}

	/**
	 * purpose: This method is used for deleting a particular label in a database.
	 * 
	 * @param tokenLabelId of the label which is to be deleted
	 * @return Response according to the result
	 */
	@Override
	public Response deleteLabel(int labelId, String tokenUserId) {

		if (repository.findById(labelId) == null)
			throw new GetLabelExcepion(NoteMessageReference.LABEL_NOT_FOUND);
		repository.delete(repository.findAll().stream().findAny().filter(i -> i.getLabelId() == labelId).get());
		return new Response(200, NoteMessageReference.LABEL_DELETE_SUCCESS, true);
	}

	@Override
	public Response getNotesOFLabel(int labelId, String tokenUserId) {
		Label label = repository.findById(labelId).orElse(null);
		if (label.getUserId() != utility.getIdFromToken(tokenUserId))
			throw new UserNotFoundException(NoteMessageReference.USER_NOT_AUTHORISED);
		if (repository.findById(labelId) == null)
			throw new GetLabelExcepion(NoteMessageReference.LABEL_NOT_FOUND);
		List<Note> notes = label.getNotes();
		return new Response(200, NoteMessageReference.NOTE_READ_SUCCES, notes);
	}

}
