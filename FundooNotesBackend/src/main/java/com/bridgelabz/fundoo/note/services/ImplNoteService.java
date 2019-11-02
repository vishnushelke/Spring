package com.bridgelabz.fundoo.note.services;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.note.model.Label;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.repository.ILabelRepository;
import com.bridgelabz.fundoo.note.repository.INoteRepository;
import com.bridgelabz.fundoo.note.userexception.ArchiveNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.CreateNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.DeleteNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.GetNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.PinNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.SortByTitleNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.SortByUpdationDateNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.TrashNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.UpdateNoteExcepion;
import com.bridgelabz.fundoo.note.utility.StaticReference;
import com.bridgelabz.fundoo.note.utility.TokenUtility;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImplNoteService implements INoteService {

	@Autowired
	private INoteRepository repository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TokenUtility utility;

	@Autowired
	private ILabelRepository labelRepository;

	/**
	 * purpose: This method is used for creating a note in a database of a
	 * particular user.
	 * 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	@Override
	public Response createNote(CreateNoteDto createNoteDto) {
		if (!(createNoteDto.getText().isBlank() && createNoteDto.getText().isBlank())) {
			Note note = mapper.map(createNoteDto, Note.class);
			repository.save(note);
			String tokenNoteId = Jwts.builder().setSubject(String.valueOf(note.getNoteId()))
					.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			return new Response(200, StaticReference.NOTE_SAVE_SUCCESS, tokenNoteId);
		}
		throw new CreateNoteExcepion(StaticReference.NOTE_CANNOT_BE_CREATED);
	}

	/**
	 * purpose: This method is used for displays notes in a database of a particular
	 * user.
	 * 
	 * @param UserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	@Override
	public Response getNote(String token) {
		int userId = utility.getIdFromToken(token);
		System.out.println(userId);
		if (!(repository.findAll().stream().anyMatch(i -> i.getUserId() == userId))) {
			throw new GetNoteExcepion(StaticReference.USER_NOT_FOUND);
		}
		Stream<Note> notesStream = repository.findAll().stream().filter(i -> i.getUserId() == userId);
		ArrayList<Note> notes = notesStream.collect(Collectors.toCollection(ArrayList::new));
		return new Response(200, StaticReference.NOTE_READ_SUCCES, notes);
	}

	/**
	 * purpose: This method is used for deleting a particular note in a database.
	 * 
	 * @param noteId of the note which is to be deleted
	 * @return Response according to the result
	 */
	@Override
	public Response deleteNote(String tokenUserId, int noteId) {
		int userId = utility.getIdFromToken(tokenUserId);
		if (!(repository.findAll().stream().anyMatch(i -> (i.getUserId() == userId) && i.getNoteId() == noteId))) {
			throw new DeleteNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		if (repository.findAll().stream().anyMatch(i -> i.getNoteId() == noteId && i.isTrash())) {
			repository.deleteById(noteId);
			return new Response(200, StaticReference.NOTE_DELETE_SUCCESS, true);
		}
		throw new DeleteNoteExcepion(StaticReference.NOTE_NOT_TRASHED);
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
			throw new UpdateNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setText(updateNoteDto.getText());
		note.setTitle(updateNoteDto.getTitle());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_UPDATE_SUCCESS, note);
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
		if (repository.findById(noteId).isEmpty()) {
			throw new ArchiveNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		if (note.isArchive()) {
			note.setArchive(false);
		} else {
			note.setPin(false);
			note.setArchive(true);
		}
		repository.save(note);
		return new Response(200, StaticReference.NOTE_ARCHIVED_SUCCESS, note);
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
		if (repository.findById(noteId).isEmpty()) {
			throw new TrashNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		if (note.isTrash()) {

			note.setTrash(false);
		} else {
			note.setPin(false);
			note.setTrash(true);
		}
		repository.save(note);
		return new Response(200, StaticReference.NOTE_TRASH_SUCCESS, note);
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
		if (repository.findById(noteId).isEmpty()) {
			throw new PinNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		if (note.isPin()) {
			note.setPin(false);
		} else {
			note.setArchive(false);
			note.setPin(true);
		}
		repository.save(note);
		return new Response(200, StaticReference.NOTE_PIN_SUCCESS, note);
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
			throw new SortByTitleNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getTitle().compareTo(n2.getTitle())).parallel();

		return new Response(200, StaticReference.NOTE_SORTED_TITLE_SUCCESS, notes);
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
			throw new SortByUpdationDateNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getNoteUpdationDate().compareTo(n2.getNoteUpdationDate())).parallel();

		return new Response(200, StaticReference.NOTE_SORTED_UPDATION_DATE_SUCCESS, notes);
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
		if (!(repository.existsById(utility.getIdFromToken(tokenUserId))))
			return new Response(200, StaticReference.USER_NOT_FOUND, false);
		Note note = repository.findById(noteId).get();
		List<Label> labels = note.getLabels();
		for (int i = 0; i < labels.size(); i++) {
			if (labels.get(i).getLabelId() == labelId) {
				return new Response(200, StaticReference.NOTE_ALREADY_ADDED_TO_LABEL, false);
			}
		}
		Label label = labelRepository.findById(labelId).get();
		labels.add(label);
		note.setLabels(labels);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_ADDED_TO_LABEL, true);
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
		if (!(repository.existsById(utility.getIdFromToken(tokenUserId))))
			return new Response(200, StaticReference.USER_NOT_FOUND, false);
		Note note = repository.findById(noteId).get();
		List<Label> labels = note.getLabels();
		labels.removeIf(i -> i.getLabelId() == labelId);
		note.setLabels(labels);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_REMOVED_FROM_LABEL, true);
	}

	/**
	 * purpose: This method is used for adding a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be added
	 * 
	 * @return Response according to the result
	 */
	@Override
	public Response addReminder(LocalDateTime reminderTime,int noteId,String tokenUserId) {
		if(!(repository.existsById(utility.getIdFromToken(tokenUserId))))
			return new Response(200, StaticReference.USER_NOT_FOUND, false);

		Note note = repository.findById(noteId).get();
		note.setReminder(reminderTime);
		repository.save(note);
		return new Response(200, StaticReference.REMINDER_SET_SUCCES, false);
	}

}
