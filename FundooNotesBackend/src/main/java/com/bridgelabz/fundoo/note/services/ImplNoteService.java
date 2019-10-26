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
import com.bridgelabz.fundoo.note.utility.StaticReference;

@Service
public class ImplNoteService implements INoteService {

	@Autowired
	INoteRepository repository;

	@Autowired
	ModelMapper mapper;

	@Override
	public Response createNote(CreateNoteDto createNoteDto) {
		// TODO Auto-generated method stub
		Note note = mapper.map(createNoteDto, Note.class);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_SAVE_SUCCESS, note);
	}

	@Override
	public Response getNote(int userId) {
		// TODO Auto-generated method stub
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == userId);
		return new Response(200, StaticReference.NOTE_READ_SUCCES, notes);
	}

	@Override
	public Response deleteNote(int noteId) {
		// TODO Auto-generated method stub
		repository.deleteById(noteId);
		return new Response(200, StaticReference.NOTE_DELETE_SUCCESS, true);
	}

	@Override
	public Response updateNote(UpdateNoteDto updateNoteDto) {
		// TODO Auto-generated method stub
		Note note = repository.findById(updateNoteDto.getNoteId()).orElse(null);
		Date date=note.getNoteCreationDate();
		note=mapper.map(updateNoteDto, Note.class);
		note.setNoteCreationDate(date);
		repository.save(note);
		return new Response(200, StaticReference.NOTE_UPDATE_SUCCESS, note);
	}
	
	

	public Note isAvailable(int userId) {
		return repository.findAll().stream().findAny().filter(i -> i.getUserId() == userId).get();
	}

	@Override
	public Response archiveUnarchiveNote(int noteId) {
		// TODO Auto-generated method stub
		Note note= repository.findById(noteId).orElse(null);
		note.setArchive(!note.getArchive());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_ARCHIVED_SUCCESS, note);
	}

	@Override
	public Response trashUntrashNote(int noteId) {
		// TODO Auto-generated method stub
		Note note= repository.findById(noteId).orElse(null);
		note.setTrash(!note.getTrash());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_TRASH_SUCCESS, note);
	}

	@Override
	public Response pinUnpinNote(int noteId) {
		// TODO Auto-generated method stub
		Note note= repository.findById(noteId).orElse(null);
		note.setPin(!note.getPin());
		repository.save(note);
		return new Response(200, StaticReference.NOTE_PIN_SUCCESS, note);
	}

	@Override
	public Response sortNoteByTitle() {
		// TODO Auto-generated method stub
//		Stream<Note> notes = 
		return null;
	}

	@Override
	public Response sortNoteByUpdationDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
