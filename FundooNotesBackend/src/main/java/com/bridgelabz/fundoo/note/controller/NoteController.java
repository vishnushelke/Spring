package com.bridgelabz.fundoo.note.controller;

import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Response> deleteNote(@RequestHeader String tokenUserId,@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.deleteNote(tokenUserId,noteId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for updating a particular note in a database. 
	 * @param updateNoteDto Data Transfer Object sent while updating a note
	 * @return Response according to the result
	 */
	@PutMapping
	public ResponseEntity<Response> updateNote(@RequestBody UpdateNoteDto updateNoteDto,@RequestHeader String tokenUserId,@RequestHeader int noteId)
	{
		return new ResponseEntity<Response>(service.updateNote(updateNoteDto,tokenUserId,noteId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for archiving/unarchiving a particular note in a database. 
	 * @param noteId of the note which is to be archived
	 * @return Response according to the result
	 */
	@PutMapping("/archive")
	public ResponseEntity<Response> archieveNote(@RequestHeader int noteId,@RequestHeader String tokenUserId )
	{
		return new ResponseEntity<Response>(service.archiveUnarchiveNote(noteId,tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for pinning/unpinning a particular note in a database. 
	 * @param noteId of the note which is to be pinned
	 * @return Response according to the result
	 */
	@PutMapping("/pin")
	public ResponseEntity<Response> pinNote(@RequestHeader int noteId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.pinUnpinNote(noteId,tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for moving/removing a particular note in a trash. 
	 * @param noteId of the note which is to be trashed
	 * @return Response according to the result
	 */
	@PutMapping("/trash")
	public ResponseEntity<Response> trashNote(@RequestHeader int noteId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.trashUntrashNote(noteId,tokenUserId),HttpStatus.OK);
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
	/**
	 * purpose: This method is used for adding note of a particular user into label
	 * 
	 * @param noteId of the user whose notes to be sorted,addNoteToLabelDto Data
	 *               transfer Object while adding note to labels
	 * @return Response according to the result
	 */
	@PutMapping("/addtolabel")
	public ResponseEntity<Response> addNoteToLabel(@RequestHeader int noteId,@RequestHeader int labelId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.addNoteToLabel(labelId,noteId,tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for removing note of a particular user from label
	 * 
	 * @param noteId of the user whose notes to be sorted,addNoteToLabelDto Data
	 *               transfer Object while adding note to labels
	 * @return Response according to the result
	 */
	@PutMapping("/removefromlabel")
	public ResponseEntity<Response> removeNoteFromLabel(@RequestHeader int noteId,@RequestHeader int labelId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.removeNoteFromLabel(labelId,noteId,tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for adding a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be added
	 * 
	 * @return Response according to the result
	 */
	@PutMapping("/addreminder")
	public ResponseEntity<Response> addReminder(@RequestHeader int noteId,@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime reminderTime,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.addReminder(reminderTime,noteId,tokenUserId),HttpStatus.OK);
	}
	 
}
