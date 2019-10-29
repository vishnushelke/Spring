package com.bridgelabz.fundoo.note.services;

import java.util.Date;
import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.note.model.Note;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.repository.INoteRepository;
import com.bridgelabz.fundoo.note.userexception.ArchiveNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.DeleteNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.GetNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.PinNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.SortByTitleNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.SortByUpdationDateNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.TrashNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.UpdateNoteExcepion;
import com.bridgelabz.fundoo.note.utility.StaticReference;

@Service
public class ImplNoteService implements INoteService {

	@Autowired
	INoteRepository repository;

	@Autowired
	ModelMapper mapper;

	/**
	 * purpose: This method is used for creating a note in a database of a particular user. 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	@Override
	public Response createNote(CreateNoteDto createNoteDto) {
		Note note = mapper.map(createNoteDto, Note.class);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_SAVE_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for displays notes in a database of a particular user. 
	 * @param UserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	@Override
	public Response getNote(int userId) {
		if (!isAvailable(userId)) {
			throw new GetNoteExcepion(StaticReference.USER_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId);
		return new Response(200, StaticReference.NOTE_READ_SUCCES, notes);
	}

	/**
	 * purpose: This method is used for deleting a particular note in a database. 
	 * @param noteId of the note which is to be deleted
	 * @return Response according to the result
	 */
	@Override
	public Response deleteNote(int noteId) {
		if (repository.findById(noteId).isEmpty()) {
			throw new DeleteNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		repository.deleteById(noteId);
		return new Response(200, StaticReference.NOTE_DELETE_SUCCESS, true);
	}

	/**
	 * purpose: This method is used for updating a particular note in a database. 
	 * @param updateNoteDto Data Transfer Object sent while updating a note
	 * @return Response according to the result
	 */
	@Override
	public Response updateNote(UpdateNoteDto updateNoteDto) {
		if (repository.findById(updateNoteDto.getNoteId()).isEmpty()) {
			throw new UpdateNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(updateNoteDto.getNoteId()).orElse(null);
		Date date = note.getNoteCreationDate();
		note = mapper.map(updateNoteDto, Note.class);
		note.setNoteCreationDate(date);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_UPDATE_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for archiving/unarchiving a particular note in a database. 
	 * @param noteId of the note which is to be archived
	 * @return Response according to the result
	 */
	@Override
	public Response archiveUnarchiveNote(int noteId) {
		if (repository.findById(noteId).isEmpty()) {
			throw new ArchiveNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setArchive(!note.getArchive());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_ARCHIVED_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for moving/removing a particular note in a trash. 
	 * @param noteId of the note which is to be trashed
	 * @return Response according to the result
	 */
	@Override
	public Response trashUntrashNote(int noteId) {
		if (repository.findById(noteId).isEmpty()) {
			throw new TrashNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setTrash(!note.getTrash());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_TRASH_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for pinning/unpinning a particular note in a database. 
	 * @param noteId of the note which is to be pinned
	 * @return Response according to the result
	 */
	@Override
	public Response pinUnpinNote(int noteId) {
		if (repository.findById(noteId).isEmpty()) {
			throw new PinNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setPin(!note.getPin());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_PIN_SUCCESS, note);
	}

	/**
	 * purpose: This method is used for sorting notes of a particular user according to title of notes in a database. 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	@Override
	public Response sortNoteByTitle(int userId) {
		if (!isAvailable(userId)) {
			throw new SortByTitleNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getTitle().compareTo(n2.getTitle())).parallel();

		return new Response(200, StaticReference.NOTE_SORTED_TITLE_SUCCESS, notes);
	}

	/**
	 * purpose: This method is used for sorting notes of a particular user according to updation date of notes in a database. 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	@Override
	public Response sortNoteByUpdationDate(int userId) {
		if (!isAvailable(userId)) {
			throw new SortByUpdationDateNoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getNoteUpdationDate().compareTo(n2.getNoteUpdationDate())).parallel();

		return new Response(200, StaticReference.NOTE_SORTED_UPDATION_DATE_SUCCESS, notes);
	}

	
	/**
	 * purpose:this method checks if the notes of a particular user are available in database and fetch the the note and returns it
	 * @param userId of the user whose notes are to be fetched
	 * @return if at least one note is available of that user then it will fetch and return that note,else it will retunr null
	 */
	public boolean isAvailable(int userId) {
		System.out.println("hiiii");
//		System.out.println(repository.findAll().stream().findAny().filter(i -> i.getUserId() == userId).get());
		return repository.findAll().stream().anyMatch(i -> i.getUserId() == userId);
	}

}
