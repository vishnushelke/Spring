package com.bridgelabz.usermanagement.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.usermanagement.exception.custom.NotAuthorizedException;
import com.bridgelabz.usermanagement.exception.custom.UserAlreadyAvailableException;
import com.bridgelabz.usermanagement.exception.custom.UserNotFoundException;
import com.bridgelabz.usermanagement.exception.custom.UserNotVerifiedException;
import com.bridgelabz.usermanagement.response.Response;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalException(Exception e){
		return new ResponseEntity<>(new Response(200, "Internal Server Error", null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException ex){
		return new ResponseEntity<>(new Response(400, "User Not Found", null),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserAlreadyAvailableException.class)
	public ResponseEntity<Response> userAlreadyAvailableException(UserAlreadyAvailableException ex){
		return new ResponseEntity<>(new Response(400, "Email Already registered", null),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(NotAuthorizedException.class)
	public ResponseEntity<Response> userNotAuthorizedException(NotAuthorizedException ex){
		return new ResponseEntity<>(new Response(400, "User not authorized", null),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotVerifiedException.class)
	public ResponseEntity<Response> userNotVerifiedException(UserNotVerifiedException ex){
		return new ResponseEntity<>(new Response(400, "Verify account to login", null),HttpStatus.BAD_REQUEST);
	}
}
