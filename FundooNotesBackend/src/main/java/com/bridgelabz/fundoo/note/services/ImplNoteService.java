package com.bridgelabz.fundoo.note.services;

import java.util.stream.Stream;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.DeleteNoteDto;
import com.bridgelabz.fundoo.note.dto.ReadNoteDto;
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
	public Response getNote(ReadNoteDto readNoteDto) {
		// TODO Auto-generated method stub
		Stream<Note> notes = repository.findAll().stream().filter(i -> i.getUserId() == (readNoteDto.getUserId()));
		return new Response(200, StaticReference.NOTE_READ_SUCCES, notes);
	}

	@Override
	public Response deleteNote(DeleteNoteDto deleteNoteDto) {
		// TODO Auto-generated method stub
		repository.deleteById(deleteNoteDto.getNoteId() & deleteNoteDto.getUserId());
		return new Response(200, StaticReference.NOTE_DELETE_SUCCESS, true);
	}

	@Override
	public Response updateNote(UpdateNoteDto updateNoteDto) {
		// TODO Auto-generated method stub
		return null;
	}

	public Note isAvailable(int userId) {
		return repository.findAll().stream().findAny().filter(i -> i.getUserId() == userId).get();
	}

}
