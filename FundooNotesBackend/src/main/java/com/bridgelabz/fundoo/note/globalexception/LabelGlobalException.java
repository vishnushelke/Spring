package com.bridgelabz.fundoo.note.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoo.note.model.Response;
import com.bridgelabz.fundoo.note.userexception.GetLabelExcepion;
@RestControllerAdvice
public class LabelGlobalException {

	/**
	 * purpose this is method is used to throw an exception when we get an exception which is 
	 * 			not declared
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(500, ex.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * purpose this is method is used to throw an exception when we get dont get the note 
	 * 			in database
	 * 
	 * @param ex Exception
	 * 
	 * @return Response according to the result
	 */
	@ExceptionHandler(GetLabelExcepion.class)
	public ResponseEntity<Response> getException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(500, ex.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}


	
	