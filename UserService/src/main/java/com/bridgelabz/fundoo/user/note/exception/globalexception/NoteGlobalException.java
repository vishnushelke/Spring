/******************************************************************************
*  Purpose: This class is major class for handling all exception which can be
*   		throw while the application is running in the server side
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   03-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.exception.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoo.user.note.exception.userexception.AddNoteToLabelExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.ArchiveNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.DeleteNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.GetNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.NoteNotFoundException;
import com.bridgelabz.fundoo.user.note.exception.userexception.PinNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.SortByTitleNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.SortByUpdationDateNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.TrashNoteExcepion;
import com.bridgelabz.fundoo.user.note.exception.userexception.UpdateNoteExcepion;
import com.bridgelabz.fundoo.user.response.Response;


@RestControllerAdvice
public class NoteGlobalException{

	/**
	 * purpose this is method is used to throw an exception when we get an exception which 
	 * 			is not declared
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Response> globalException(Exception ex)
//	{
//		return new ResponseEntity<Response>(new Response(500, ex.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	/**
	 * purpose this is method is used to throw an exception when we get an exception while 
	 * 			getting user notes
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	
	@ExceptionHandler(GetNoteExcepion.class)
	public ResponseEntity<Response> getNotesException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while 
	 * 			updating a note
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(UpdateNoteExcepion.class)
	public ResponseEntity<Response> updateNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while 
	 * 			deleting a note
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(DeleteNoteExcepion.class)
	public ResponseEntity<Response> deleteNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while 
	 * 			archiving a note
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(ArchiveNoteExcepion.class)
	public ResponseEntity<Response> archiveNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception 
	 * 			while pinning a note
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(PinNoteExcepion.class)
	public ResponseEntity<Response> pinNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while 
	 * 			trashing a note
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(TrashNoteExcepion.class)
	public ResponseEntity<Response> trashNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception 
	 * 			while sorting notes by title
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(SortByTitleNoteExcepion.class)
	public ResponseEntity<Response> sortByTitleNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception 
	 * 			while sorting notes by updation Date
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(SortByUpdationDateNoteExcepion.class)
	public ResponseEntity<Response> sortByUpdationDateNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception 
	 * 			while adding notes to label
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(AddNoteToLabelExcepion.class)
	public ResponseEntity<Response> addNoteToLabelException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we dont get note in database
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(NoteNotFoundException.class)
	public ResponseEntity<Response> noteNotFoundException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
}
