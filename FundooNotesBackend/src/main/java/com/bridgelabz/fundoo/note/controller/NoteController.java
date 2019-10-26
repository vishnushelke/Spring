package com.bridgelabz.fundoo.note.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.UpdateNoteDto;
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
	
	@GetMapping
	public ResponseEntity<Response> getNotes(@RequestHeader int userId)
	{
		return new ResponseEntity<Response>(service.getNote(userId),HttpStatus.OK);
	}
	
	@DeleteMapping
	public ResponseEntity<Response> deleteNote(@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.deleteNote(noteId),HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDto updateNoteDto)
	{
		return new ResponseEntity<Response>(service.updateNote(updateNoteDto),HttpStatus.OK);
	}
	@PutMapping("/archive")
	public ResponseEntity<Response> archieveNote(@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.archiveUnarchiveNote(noteId),HttpStatus.OK);
	}
	@PutMapping("/pin")
	public ResponseEntity<Response> pinNote(@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.pinUnpinNote(noteId),HttpStatus.OK);
	}
	@PutMapping("/trash")
	public ResponseEntity<Response> trashNote(@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.trashUntrashNote(noteId),HttpStatus.OK);
	}
	@GetMapping("sortbyupdationdate")
	public ResponseEntity<Response> sortByUpdationDate(@RequestHeader int userId)
	{
		return new ResponseEntity<Response>(service.sortNoteByUpdationDate(userId),HttpStatus.OK);
	}
	@GetMapping("sortbytitle")
	public ResponseEntity<Response> sortByTitle(@RequestHeader int userId)
	{
		return new ResponseEntity<Response>(service.sortNoteByTitle(userId),HttpStatus.OK);
	}
}
