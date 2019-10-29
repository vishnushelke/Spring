package com.bridgelabz.fundoo.note.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.userexception.ArchiveNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.DeleteNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.GetNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.PinNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.SortByTitleNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.SortByUpdationDateNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.TrashNoteExcepion;
import com.bridgelabz.fundoo.note.userexception.UpdateNoteExcepion;

@RestControllerAdvice
public class Global{

	/**
	 * purpose this is method is used to throw an exception when we get an exception which is not declared
	 * @param ex Exception
	 * @return Response according to the result
	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Response> globalException(Exception ex)
//	{
//		return new ResponseEntity<Response>(new Response(500, ex.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
//	}
	
	/**
	 * purpose this is method is used to throw an exception when we get an exception while getting user notes
	 * @param ex Exception
	 * @return Response according to the result
	 */
	
	@ExceptionHandler(GetNoteExcepion.class)
	public ResponseEntity<Response> getNotesException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while updating a note
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(UpdateNoteExcepion.class)
	public ResponseEntity<Response> updateNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while deleting a note
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(DeleteNoteExcepion.class)
	public ResponseEntity<Response> deleteNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while archiving a note
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(ArchiveNoteExcepion.class)
	public ResponseEntity<Response> archiveNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while pinning a note
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(PinNoteExcepion.class)
	public ResponseEntity<Response> pinNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while trashing a note
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(TrashNoteExcepion.class)
	public ResponseEntity<Response> trashNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while sorting notes by title
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(SortByTitleNoteExcepion.class)
	public ResponseEntity<Response> sortByTitleNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose this is method is used to throw an exception when we get an exception while sorting notes by updation Date
	 * @param ex Exception
	 * @return Response according to the result
	 */
	@ExceptionHandler(SortByUpdationDateNoteExcepion.class)
	public ResponseEntity<Response> sortByUpdationDateNoteException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
}
