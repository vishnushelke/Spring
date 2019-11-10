/******************************************************************************
*  Purpose: This class implements all methods of INoteService interface
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.user.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.user.note.exception.userexception.CreateNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.DeleteNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.GetNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.NoteNotFoundException;
import com.bridgelabz.fundoo.user.note.model.Label;
import com.bridgelabz.fundoo.user.note.model.Note;
import com.bridgelabz.fundoo.user.note.repository.ILabelRepository;
import com.bridgelabz.fundoo.user.note.repository.INoteRepository;
import com.bridgelabz.fundoo.user.note.utility.NoteMessageReference;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.note.utility.NoteTokenUtility;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.services.MessageReference;

@Service
public class ImplNoteService implements INoteService {

	@Autowired
	private INoteRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private NoteTokenUtility utility;

	@Autowired
	private ILabelRepository labelRepository;

	@Autowired
	private UserRepository userRepository;

	/**
	 * purpose: This method is used for creating a note in a database of a
	 * particular user.
	 * 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	@Override
	public Response createNote(CreateNoteDto createNoteDto,String tokenUserId) {
		if (!(createNoteDto.getText().isBlank() && createNoteDto.getText().isBlank())) {
			if(userRepository.findById(utility.getIdFromToken(tokenUserId)).isEmpty())
				throw new UserNotFoundException(NoteMessageReference.USER_NOT_FOUND);
			repository.save(mapper.map(createNoteDto, Note.class));
			return new Response(200, NoteMessageReference.NOTE_SAVE_SUCCESS, true);
		}
		throw new CreateNoteExcepion(NoteMessageReference.NOTE_CANNOT_BE_CREATED);
	}

	/**
	 * purpose: This method is used for displays notes in a database of a particular
	 * user.
	 * 
	 * @param UserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	@Override
	public Response getNote(String tokenUserId) {
		int userId = utility.getIdFromToken(tokenUserId);
		if ((repository.findById(userId)).isEmpty()) {
			throw new GetNoteExcepion(NoteMessageReference.USER_NOT_FOUND);
		}
		Stream<Note> notesStream = repository.findAll().stream().filter(i -> i.getUserId() == userId);
		ArrayList<Note> notes = notesStream.collect(Collectors.toCollection(ArrayList::new));
		return new Response(200, NoteMessageReference.NOTE_READ_SUCCES, notes);
	}

	/**
	 * purpose: This method is used for deleting a particular note in a database.
	 * 
	 * @param noteId of the note which is to be deleted
	 * @return Response according to the result
	 */
	@Override
	public Response deleteNote(String tokenUserId, int noteId) {
		Note note = repository.findById(noteId).orElse(null);
		if ((note==null)) 
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		if(note.getUserId()==utility.getIdFromToken(tokenUserId))
			throw new UserNotFoundException(NoteMessageReference.USER_NOT_FOUND);
		if (note.isTrash()) {
			repository.deleteById(noteId);
			return new Response(200, NoteMessageReference.NOTE_DELETE_SUCCESS, true);
		}
		throw new DeleteNoteExcepion(NoteMessageReference.NOTE_NOT_TRASHED);
	}

	/**
	 * purpose: This method is used for updating a particular note in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a note
	 * @return Response according to the result
	 */
	@Override
	public Response updateNote(UpdateNoteDto updateNoteDto, String tokenUserId, int noteId) {

		int userId = utility.getIdFromToken(tokenUserId);
		if (!(repository.findAll().stream().anyMatch(i -> (i.getNoteId() == noteId) && i.getUserId() == userId))) {
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setText(updateNoteDto.getText());
		note.setTitle(updateNoteDto.getTitle());
		repository.save(note);
		return new Response(200, NoteMessageReference.NOTE_UPDATE_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for archiving/unarchiving a particular note in a
	 * database.
	 * 
	 * @param noteId of the note which is to be archived
	 * @return Response according to the result
	 */
	@Override
	public Response archiveUnarchiveNote(int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).orElse(null);
		if (note.isArchive()) {
			note.setArchive(false);
		} else {
			note.setPin(false);
			note.setArchive(true);
		}
		repository.save(note);
		return new Response(200, NoteMessageReference.NOTE_ARCHIVED_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for moving/removing a particular note in a
	 * trash.
	 * 
	 * @param noteId of the note which is to be trashed
	 * @return Response according to the result
	 */
	@Override
	public Response trashUntrashNote(int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).orElse(null);
		if (note.isTrash()) {

			note.setTrash(false);
		} else {
			note.setPin(false);
			note.setTrash(true);
		}
		repository.save(note);
		return new Response(200, NoteMessageReference.NOTE_TRASH_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for pinning/unpinning a particular note in a
	 * database.
	 * 
	 * @param noteId of the note which is to be pinned
	 * @return Response according to the result
	 */
	@Override
	public Response pinUnpinNote(int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).orElse(null);
		if (note.isPin()) {
			note.setPin(false);
		} else {
			note.setArchive(false);
			note.setPin(true);
		}
		repository.save(note);
		return new Response(200, NoteMessageReference.NOTE_PIN_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for sorting notes of a particular user according
	 * to title of notes in a database.
	 * 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	@Override
	public Response sortNoteByTitle(String tokenUserId) {
		int userId = utility.getIdFromToken(tokenUserId);
		if (!(repository.findAll().stream().anyMatch(i -> i.getUserId() == userId))) {
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getTitle().compareTo(n2.getTitle())).parallel();

		return new Response(200, NoteMessageReference.NOTE_SORTED_TITLE_SUCCESS, notes);
	}

	/**
	 * purpose: This method is used for sorting notes of a particular user according
	 * to updation date of notes in a database.
	 * 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	@Override
	public Response sortNoteByUpdationDate(String tokenUserId) {
		int userId = utility.getIdFromToken(tokenUserId);
		if (!(repository.findAll().stream().anyMatch(i -> i.getUserId() == userId))) {
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getNoteUpdationDate().compareTo(n2.getNoteUpdationDate())).parallel();

		return new Response(200, NoteMessageReference.NOTE_SORTED_UPDATION_DATE_SUCCESS, notes);
	}

	/**
	 * purpose: This method is used for adding note of a particular user into label
	 * 
	 * @param noteId of the user whose notes to be sorted,addNoteToLabelDto Data
	 *               transfer Object while adding note to labels
	 * @return Response according to the result
	 */
	@Override
	public Response addNoteToLabel(int labelId, int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
//		if (repository.findById(noteId) == null)
//			throw new NoteNotFoundException(UserMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).orElseThrow(NoteNotFoundException::new);
		List<Label> labels = note.getLabels();
		for (int i = 0; i < labels.size(); i++) {
			if (labels.get(i).getLabelId() == labelId) {
				return new Response(200, NoteMessageReference.NOTE_ALREADY_ADDED_TO_LABEL, false);
			}
		}
		Label label = labelRepository.findById(labelId).get();
		labels.add(label);
		note.setLabels(labels);
		repository.save(note);
		return new Response(200, NoteMessageReference.NOTE_ADDED_TO_LABEL, true);
	}

	/**
	 * purpose: This method is used for removing note of a particular user from
	 * label
	 * 
	 * @param noteId of the user whose notes to be sorted,addNoteToLabelDto Data
	 *               transfer Object while adding note to labels
	 * @return Response according to the result
	 */

	public Response removeNoteFromLabel(int labelId, int noteId, String tokenUserId) {
		System.out.println(repository.findById(noteId).toString());
		if (repository.findById(noteId).isEmpty())
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		

		Note note = repository.findById(noteId).get();
		List<Label> labels = note.getLabels();
		labels.removeIf(i -> i.getLabelId() == labelId);
		note.setLabels(labels);
		repository.save(note);
		return new Response(200, NoteMessageReference.NOTE_REMOVED_FROM_LABEL, true);
	}

	/**
	 * purpose: This method is used for adding a reminder of note of a particular
	 * user
	 * 
	 * @param noteId of the note whose reminder is to be added
	 * 
	 * @return Response according to the result
	 */
	@Override
	public Response addReminder(LocalDateTime reminderTime, int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).get();
		note.setReminder(reminderTime);
		repository.save(note);
		return new Response(200, NoteMessageReference.REMINDER_SET_SUCCESS, false);
	}

	/**
	 * purpose: This method is used for updating a reminder of note of a particular
	 * user
	 * 
	 * @param noteId of the note whose reminder is to be updated
	 * 
	 * @return Response according to the result
	 */
	@Override
	public Response updateReminder(LocalDateTime reminderTime, int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).get();
		note.setReminder(reminderTime);
		repository.save(note);
		return new Response(200, NoteMessageReference.LABEL_UPDATE_SUCCESS, false);
	}

	/**
	 * purpose: This method is used for removing a reminder of note of a particular
	 * user
	 * 
	 * @param noteId of the note whose reminder is to be removed
	 * 
	 * @return Response according to the result
	 */
	@Override
	public Response removeReminder(int noteId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).get();
		note.setReminder(null);
		repository.save(note);
		return new Response(200, NoteMessageReference.REMINDER_REMOVE_SUCCESS, false);
	}

	/**
	 * purpose: This method is used for collaborating a note of a particular user
	 * 
	 * @param noteId      id of the note which is to be collaborated
	 * @param emailId     of the user with whom we are collaborating the note
	 * @param tokenUserId token sent to mail
	 * 
	 * @return Response according to the result
	 */
	@Override
	public Response addCollaborator(int noteId, String emailId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		//TODO 
		Note note = repository.findById(noteId).get();
		List<User> userCollab = note.getCollabUsers();
		User user = userRepository.findAll().stream().filter(i -> i.getEmail().equals(emailId)).findAny().get();
		userCollab.add(user);
		note.setCollabUsers(userCollab);
		repository.save(note);
		return new Response(200, NoteMessageReference.COLLABORATION_SUCCESS, true);
	}

	/**
	 * purpose: This method is used for rmoving collaboration of a note of a
	 * particular user
	 * 
	 * @param noteId      id of the note which is to be collaborated
	 * @param emailId     of the user with whom we are removing collaboration of the
	 *                    note
	 * @param tokenUserId token sent to mail
	 * 
	 * @return Response according to the result
	 */
	@Override
	public Response removeCollaborator(int noteId, String emailId, String tokenUserId) {
		if (!(repository.findById(noteId).get().getUserId() == utility.getIdFromToken(tokenUserId)))
			return new Response(200, NoteMessageReference.USER_NOT_FOUND, false);
		if (repository.findById(noteId) == null)
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);

		Note note = repository.findById(noteId).get();
		List<User> userCollab = note.getCollabUsers();
		userCollab.removeIf(i->i.getEmail().equals(emailId));
		note.setCollabUsers(userCollab);
		repository.save(note);
		return new Response(200, NoteMessageReference.COLLABORATION_REMOVE_SUCCESS, true);
	}

	@Override
	public Response setColour(int noteId, String colourHashCode, String tokenUserId) {
		Note note = repository.findById(noteId).orElse(null);
		if(note.equals(null))
			throw new NoteNotFoundException(NoteMessageReference.NOTE_NOT_FOUND);
		if(note.getUserId()!=utility.getIdFromToken(tokenUserId))
			throw new UserNotFoundException(NoteMessageReference.USER_NOT_FOUND);
		note.setColour(colourHashCode);
		repository.save(note);
		return new Response(200, NoteMessageReference.COLOUR_SET_SUCCESS, true);
	}

}
