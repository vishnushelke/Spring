package com.bridgelabz.fundoo.user.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bridgelabz.fundoo.user.exception.custom.ForgetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.ValidationException;
import com.bridgelabz.fundoo.user.response.ErrorResponse;

@RestControllerAdvice
public class GlobalException {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> globalExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse(500, "Internal Server Error", null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorResponse> loginExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse(500, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<ErrorResponse> registrationExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse(500, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<ErrorResponse> validationExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse(500, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(ForgetPasswordException.class)
	public ResponseEntity<ErrorResponse> forgetPasswordExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new ErrorResponse(500, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
}
