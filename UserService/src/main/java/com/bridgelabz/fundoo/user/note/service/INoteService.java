/******************************************************************************
*  Purpose: This is an interface which has all methods which we want to implement
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   02-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.service;

import java.time.LocalDateTime;
import java.util.Date;

import com.bridgelabz.fundoo.user.note.dto.CreateNoteDto;
import com.bridgelabz.fundoo.user.note.dto.UpdateNoteDto;
import com.bridgelabz.fundoo.user.response.Response;



public interface INoteService {

	/**
	 * purpose: This method is used for creating a note in a database of a
	 * particular user.
	 * 
	 * @param createNoteDto Data Transfer Object sent while creating a note
	 * @return Response according to the result
	 */
	public Response createNote(CreateNoteDto createNoteDto,String tokenUserId);

	/**
	 * purpose: This method is used for displaying notes in a database of a particular
	 * user which are not trashed and not archived.
	 * 
	 * @param tokenUserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	public Response getNote(String tokenUserId);
	
	/**
	 * purpose: This method is used for displaying notes in a database of a particular
	 * user which are archived.
	 * 
	 * @param tokenUserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	public Response getArchivedNotes(String tokenUserId);
	
	/**
	 * purpose: This method is used for displays notes in a database of a particular
	 * user ehich are trashed.
	 * 
	 * @param tokenUserId of the user whose notes to be displayed
	 * @return Response according to the result
	 */
	public Response getTrashedNotes(String tokenUserId);

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
	public Response addNoteToLabel(String name, int noteId,String tokenUserId);

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
	public Response addReminder(Date reminderTime,int noteId,String tokenUserId);
	
	/**
	 * purpose: This method is used for updating a reminder of note of a particular
	 * 			user
	 * 
	 * @param noteId of the note whose reminder is to be updated
	 * 
	 * @return Response according to the result
	 */
	public Response updateReminder(Date reminderTime, int noteId, String tokenUserId);
	
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
	 *  purpose: This method is used for collaborating a note of a particular
	 * 			user
	 * 
	 * @param noteId id of the note which is to be collaborated
	 * @param emailId of the user with whom we are collaborating the note
	 * @param tokenUserId token sent to mail
	 * 
	 * @return Response according to the result
	 */
	public Response addCollaborator(int noteId,String emailId,String tokenUserId);
	
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
	public Response removeCollaborator(int noteId,String emailId,String tokenUserId);
	/**
	 *  purpose: This method is used for setting colour of a note of a particular
	 * 			user
	 * 
	 * @param noteId id of the note which is to be collaborated
	 * @param colourHashCode of the colour
	 * @param tokenUserId token sent to mail
	 * 
	 * @return Response according to the result
	 */
	public Response setColour(int noteId,String colourHashCode,String tokenUserId);
	
	public Response searchNotesByTitle(String title, String tokenUserId);
	
	public Response getReminderNotes(String tokenUserId);
}
