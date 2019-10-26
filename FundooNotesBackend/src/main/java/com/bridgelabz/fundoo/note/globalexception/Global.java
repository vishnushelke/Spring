package com.bridgelabz.fundoo.note.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoo.note.model.Response;

@RestControllerAdvice
public class Global{

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalException(Exception ex)
	{
		return new ResponseEntity<Response>(new Response(500, ex.getMessage(), null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
