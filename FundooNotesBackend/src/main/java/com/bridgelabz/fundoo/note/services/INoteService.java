package com.bridgelabz.fundoo.note.services;

import java.time.LocalDateTime;

import com.bridgelabz.fundoo.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.note.model.Response;

public interface INoteService {

	/**
	 * purpose: This method is used for creating a note in a database of a
	 * particular user.
	 * 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	public Response createNote(CreateNoteDto createNoteDto);

	/**
	 * purpose: This method is used for displays notes in a database of a particular
	 * user.
	 * 
	 * @param tokenUserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	public Response getNote(String tokenUserId);

	/**
	 * purpose: This method is used for deleting a particular note in a database.
	 * 
	 * @param noteId of the note which is to be deleted
	 * @return Response according to the result
	 */
	public Response deleteNote(String tokenUserId, int noteId);

	/**
	 * purpose: This method is used for updating a particular note in a database.
	 * 
	 * @param updateNoteDto Data Transfer Object sent while updating a note
	 * @return Response according to the result
	 */
	public Response updateNote(UpdateNoteDto updateNoteDto, String tokenUserId, int noteId);

	/**
	 * purpose: This method is used for archiving/unarchiving a particular note in a
	 * database.
	 * 
	 * @param noteId of the note which is to be archived
	 * @return Response according to the result
	 */
	public Response archiveUnarchiveNote(int noteId, String token);

	/**
	 * purpose: This method is used for moving/removing a particular note in a
	 * trash.
	 * 
	 * @param noteId of the note which is to be trashed
	 * @return Response according to the result
	 */
	public Response trashUntrashNote(int noteId, String token);

	/**
	 * purpose: This method is used for pinning/unpinning a particular note in a
	 * database.
	 * 
	 * @param noteId of the note which is to be pinned
	 * @return Response according to the result
	 */
	public Response pinUnpinNote(int noteId, String token);

	/**
	 * purpose: This method is used for sorting notes of a particular user according
	 * to title of notes in a database.
	 * 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	public Response sortNoteByTitle(String token);

	/**
	 * purpose: This method is used for sorting notes of a particular user according
	 * to updation date of notes in a database.
	 * 
	 * @param userId of the user whose notes to be sorted
	 * @return Response according to the result
	 */
	public Response sortNoteByUpdationDate(String token);

	/**
	 * purpose: This method is used for adding note of a particular user into label
	 * 
	 * @param noteId of the note to be added,labelId Id of label to
	 *               which note is to be added
	 * @return Response according to the result
	 */
	public Response addNoteToLabel(int labelId, int noteId,String tokenUserId);

	/**
	 * purpose: This method is used for removing note of a particular user from
	 * label
	 * 
	 * @param noteId of the note,labelId Id of label from
	 *               which note is to be removed
	 * @return Response according to the result
	 */
	public Response removeNoteFromLabel(int labelId, int noteId,String tokenUserId);

	/**
	 * purpose: This method is used for adding a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be added
	 * 
	 * @return Response according to the result
	 */
	public Response addReminder(LocalDateTime reminderTime,int noteId,String tokenUserId);
	
	/**
	 * purpose: This method is used for updating a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be updated
	 * 
	 * @return Response according to the result
	 */
	public Response updateReminder(LocalDateTime reminderTime, int noteId, String tokenUserId);
	
	/**
	 * purpose: This method is used for removing a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be removed
	 * 
	 * @return Response according to the result
	 */
	public Response removeReminder(int noteId, String tokenUserId);
	
	/**
	 * purpose: This method is used for adding an image to note of a particular
	 * 			user
	 * 
	 * @param noteId of the note in which image is to be added
	 * 
	 * @return Response according to the result
	 */
	public Response addImage(String filePath);
}
