package com.bridgelabz.fundoo.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.DeleteNoteDto;
import com.bridgelabz.fundoo.note.dto.ReadNoteDto;
import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.services.ImplNoteService;

@RestController
@RequestMapping("/user/notes")
public class NoteController {

	@Autowired
	ImplNoteService service;
	
	@PostMapping
	public ResponseEntity<Response> createNote(@RequestBody CreateNoteDto createNoteDto)
	{
		return new ResponseEntity<Response>(service.createNote(createNoteDto),HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<Response> getNotes(@RequestBody ReadNoteDto readNoteDto)
	{
		return new ResponseEntity<Response>(service.getNote(readNoteDto),HttpStatus.OK);
	}
	
	@DeleteMapping("/{userId}/{noteId}")
	public ResponseEntity<Response> deleteNote(@RequestHeader int userId,@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.deleteNote(deleteNodeDto),HttpStatus.OK);
	}
	
}
