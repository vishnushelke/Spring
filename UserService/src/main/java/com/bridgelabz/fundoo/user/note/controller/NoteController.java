/******************************************************************************
*  Purpose: This is a controller class of Notes
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-11-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.user.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.user.note.service.ImplNoteService;
import com.bridgelabz.fundoo.user.response.Response;



@RestController
@RequestMapping("/user/notes")
@CrossOrigin
public class NoteController {

	@Autowired
	private ImplNoteService service;
	
	/**
	 * purpose: This method is used for creating a note in a database of a particular user. 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	@PostMapping
	public ResponseEntity<Response> createNote(@RequestBody CreateNoteDto createNoteDto,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.createNote(createNoteDto,tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for displaying notes in a database of a particular user which are archived. 
	 * @param UserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	@GetMapping("/archivednotes")
	public ResponseEntity<Response> getArchivedNotes(@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.getArchivedNotes(tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for displaying notes in a database of a particular user which are trashed. 
	 * @param UserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	@GetMapping("/trashednotes")
	public ResponseEntity<Response> getTrashedNotes(@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.getTrashedNotes(tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for displaying notes in a database of a particular user. 
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
	public ResponseEntity<Response> addReminder(@RequestHeader int noteId,@RequestHeader Date reminderTime,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.addReminder(reminderTime,noteId,tokenUserId),HttpStatus.OK);
	}
	
	/**
	 * purpose: This method is used for updating a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be added
	 * 
	 * @return Response according to the result
	 */
	@PutMapping("/updatereminder")
	public ResponseEntity<Response> updateReminder(@RequestHeader int noteId,@RequestHeader Date reminderTime,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.updateReminder(reminderTime,noteId,tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for updating a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be added
	 * 
	 * @return Response according to the result
	 */
	@PutMapping("/removereminder")
	public ResponseEntity<Response> removeReminder(@RequestHeader int noteId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.removeReminder(noteId,tokenUserId),HttpStatus.OK);
	}
	/**
	 * purpose: This method is used for collaborating a note of a particular
	 * 			user with other users
	 * 
	 * @param noteId of the note whose collaboration is to be added
	 * @param emailId of the user with we are collaborating
	 * @param tokenUserId of the user
	 * 
	 * @return Response according to the result
	 */
	@PutMapping("/addcollab")
	public ResponseEntity<Response> addCollaborator(@RequestHeader int noteId,@RequestHeader String emailId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.addCollaborator(noteId, emailId, tokenUserId),HttpStatus.OK);
	}
	/**
	 *  purpose: This method is used for removing collaboration of a note of a particular
	 * 			user
	 * 
	 * @param noteId id of the note which is to be collaborated
	 * @param emailId of the user with whom we are removing collaboration of the note
	 * @param tokenUserId token sent to mail
	 * 
	 * @return Response according to the result
	 */
	@PutMapping("/removecollab")
	public ResponseEntity<Response> removeCollaborator(@RequestHeader int noteId,@RequestHeader String emailId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.removeCollaborator(noteId, emailId, tokenUserId),HttpStatus.OK);
	}
	@PutMapping("/setcolour")
	public ResponseEntity<Response> setColour(@RequestHeader String colourHashcode,@RequestHeader int noteId,@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.setColour(noteId, colourHashcode, tokenUserId),HttpStatus.OK);
	}
	@GetMapping("/searchnotes")
	public ResponseEntity<Response> searchNotesByTitle(@RequestHeader String title,@RequestHeader String tokenUserId)
	{
		System.out.println("in search note");
		return new ResponseEntity<Response>(service.searchNotesByTitle(title,tokenUserId),HttpStatus.OK);
	}
	@GetMapping("/getremindernotes")
	public ResponseEntity<Response> getReminderNotes(@RequestHeader String tokenUserId)
	{
		return new ResponseEntity<Response>(service.getReminderNotes(tokenUserId),HttpStatus.OK);
	}
}
