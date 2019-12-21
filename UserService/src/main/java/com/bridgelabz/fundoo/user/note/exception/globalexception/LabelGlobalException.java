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

import com.bridgelabz.fundoo.user.note.exception.userexception.LabelNotFound;
import com.bridgelabz.fundoo.user.note.utility.NoteMessageReference;
import com.bridgelabz.fundoo.user.response.Response;

@RestControllerAdvice
public class LabelGlobalException {

	/**
	 * purpose this is method is used to throw an exception when we get an exception
	 * which is not declared
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
//	@ExceptionHandler(Exception.class)
//	public ResponseEntity<Response> globalException(Exception ex) {
//		return new ResponseEntity<Response>(new Response(500, ex.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
//	}

	/**
	 * purpose this is method is used to throw an exception when we get dont get the
	 * note in database
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(LabelNotFound.class)
	public ResponseEntity<Response> getException(Exception ex) {
		return new ResponseEntity<Response>(new Response(500, NoteMessageReference.LABEL_NOT_FOUND, null), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
