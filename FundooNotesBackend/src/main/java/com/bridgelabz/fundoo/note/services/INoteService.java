package com.bridgelabz.fundoo.note.services;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.note.model.Response;

public interface INoteService {
	
	public Response createNote(CreateNoteDto createNoteDto);
	
	public Response getNote(int userId);
	
	public Response deleteNote(int NoteId);
	
	public Response updateNote(UpdateNoteDto updateNoteDto);
	
	public Response archiveUnarchiveNote(int NoteId);
	
	public Response trashUntrashNote(int NoteId);
	
	public Response pinUnpinNote(int NoteId);
	
	public Response sortNoteByTitle(int userId);
	
	public Response sortNoteByUpdationDate(int userId);
}
