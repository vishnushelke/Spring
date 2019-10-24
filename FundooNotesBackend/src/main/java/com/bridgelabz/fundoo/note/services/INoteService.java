package com.bridgelabz.fundoo.note.services;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.DeleteNoteDto;
import com.bridgelabz.fundoo.note.dto.ReadNoteDto;
import com.bridgelabz.fundoo.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.note.model.Response;

public interface INoteService {
	
	public Response createNote(CreateNoteDto createNoteDto);
	
	public Response getNote(ReadNoteDto readNoteDto);
	
	public Response deleteNote(DeleteNoteDto deleteNoteDto);
	
	public Response updateNote(UpdateNoteDto updateNoteDto);
}
