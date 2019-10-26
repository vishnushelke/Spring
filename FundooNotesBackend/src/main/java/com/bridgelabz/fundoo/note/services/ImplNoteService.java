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
import com.bridgelabz.fundoo.note.userexception.NoteExcepion;
import com.bridgelabz.fundoo.note.utility.StaticReference;

@Service
public class ImplNoteService implements INoteService {

	@Autowired
	INoteRepository repository;

	@Autowired
	ModelMapper mapper;

	@Override
	public Response createNote(CreateNoteDto createNoteDto) {
		Note note = mapper.map(createNoteDto, Note.class);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_SAVE_SUCCESS, note);
	}

	@Override
	public Response getNote(int userId) {
		if (isAvailable(userId) == null) {
			throw new NoteExcepion(StaticReference.USER_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId);
		return new Response(200, StaticReference.NOTE_READ_SUCCES, notes);
	}

	@Override
	public Response deleteNote(int noteId) {
		if (repository.findById(noteId) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		repository.deleteById(noteId);
		return new Response(200, StaticReference.NOTE_DELETE_SUCCESS, true);
	}

	@Override
	public Response updateNote(UpdateNoteDto updateNoteDto) {
		if (repository.findById(updateNoteDto.getNoteId()) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(updateNoteDto.getNoteId()).orElse(null);
		Date date = note.getNoteCreationDate();
		note = mapper.map(updateNoteDto, Note.class);
		note.setNoteCreationDate(date);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_UPDATE_SUCCESS, note);
	}

	@Override
	public Response archiveUnarchiveNote(int noteId) {
		if (repository.findById(noteId) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setArchive(!note.getArchive());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_ARCHIVED_SUCCESS, note);
	}

	@Override
	public Response trashUntrashNote(int noteId) {
		if (repository.findById(noteId) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setTrash(!note.getTrash());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_TRASH_SUCCESS, note);
	}

	@Override
	public Response pinUnpinNote(int noteId) {
		if (repository.findById(noteId) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Note note = repository.findById(noteId).orElse(null);
		note.setPin(!note.getPin());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_PIN_SUCCESS, note);
	}

	@Override
	public Response sortNoteByTitle(int userId) {
		if (repository.findById(userId) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getTitle().compareTo(n2.getTitle())).parallel();

		return new Response(200, StaticReference.NOTE_SORTED_TITLE_SUCCESS, notes);
	}

	@Override
	public Response sortNoteByUpdationDate(int userId) {
		if (repository.findById(userId) == null) {
			throw new NoteExcepion(StaticReference.NOTE_NOT_FOUND);
		}
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId)
				.sorted((Note n1, Note n2) -> n1.getNoteUpdationDate().compareTo(n2.getNoteUpdationDate())).parallel();

		return new Response(200, StaticReference.NOTE_SORTED_UPDATION_DATE_SUCCESS, notes);
	}

	public Note isAvailable(int userId) {
		return repository.findAll().stream().findAny().filter(i -> i.getUserId() == userId).get();
	}

}
