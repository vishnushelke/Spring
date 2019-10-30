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
	
	/**
	 * purpose: This method is used for creating a note in a database of a particular user. 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	@PostMapping
	public ResponseEntity<Response> createNote(@RequestBody CreateNoteDto createNoteDto)
	{
		return new ResponseEntity<Response>(service.createNote(createNoteDto),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for displays notes in a database of a particular user. 
	 * @param UserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	@GetMapping
	public ResponseEntity<Response> getNotes(@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.getNote(tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for deleting a particular note in a database. 
	 * @param noteId of the note which is to be deleted
	 * @return Response according to the result
	 */
	@DeleteMapping
	public ResponseEntity<Response> deleteNote(@RequestHeader String tokenUserId,@RequestHeader String tokenNoteId)
	{
		return new ResponseEntity<Response>(service.deleteNote(tokenUserId,tokenNoteId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for updating a particular note in a database. 
	 * @param updateNoteDto Data Transfer Object sent while updating a note
	 * @return Response according to the result
	 */
	@PutMapping
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDto updateNoteDto,@RequestHeader String tokenUserId,@RequestHeader String tokenNoteId)
	{
		return new ResponseEntity<Response>(service.updateNote(updateNoteDto,tokenUserId,tokenNoteId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for archiving/unarchiving a particular note in a database. 
	 * @param noteId of the note which is to be archived
	 * @return Response according to the result
	 */
	@PutMapping("/archive")
	public ResponseEntity<Response> archieveNote(@RequestHeader String tokenNoteId,@RequestHeader String tokenUserId )
	{
		return new ResponseEntity<Response>(service.archiveUnarchiveNote(tokenNoteId,tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for pinning/unpinning a particular note in a database. 
	 * @param noteId of the note which is to be pinned
	 * @return Response according to the result
	 */
	@PutMapping("/pin")
	public ResponseEntity<Response> pinNote(@RequestHeader String tokenNoteId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.pinUnpinNote(tokenNoteId,tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for moving/removing a particular note in a trash. 
	 * @param noteId of the note which is to be trashed
	 * @return Response according to the result
	 */
	@PutMapping("/trash")
	public ResponseEntity<Response> trashNote(@RequestHeader String tokenNoteId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.trashUntrashNote(tokenNoteId,tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for sorting notes of a particular user according to updation date of notes in a database. 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	@GetMapping("sortbyupdationdate")
	public ResponseEntity<Response> sortByUpdationDate(@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.sortNoteByUpdationDate(tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for sorting notes of a particular user according to title of notes in a database. 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	@GetMapping("sortbytitle")
	public ResponseEntity<Response> sortByTitle(@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.sortNoteByTitle(tokenUserId),HttpStatus.OK);
	}
}
