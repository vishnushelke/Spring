/******************************************************************************
 													*  Purpose: This class is major class for handling all exception which can be
*   throw while the application is running in the server side, as this
*   class is global exception handler it will handle custom exception
*   and built in exception also, this class purpose is to handle
*   application from crashing even in critical situation and giving
*   proper response if crashing.
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.exception.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.bridgelabz.fundoo.user.exception.custom.ForgetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.NotActiveException;
import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.UserNotFound;
import com.bridgelabz.fundoo.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundoo.user.exception.custom.ValidationException;
import com.bridgelabz.fundoo.user.note.utility.NoteMessageReference;
import com.bridgelabz.fundoo.user.response.Response;

@RestControllerAdvice
public class GlobalException {

	/**
	 * purpose: This will be used when you want to throw a global exception
	 * @param ex exception
	 * @return response entity of global exception
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Response> globalExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(500,"internal server error", null),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * purpose: This will be used when you want to throw a login exception
	 * @param ex exception
	 * @return response entity of login exception
	 */
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<Response> loginExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}

	/**
	 * purpose: This will be used when you want to throw a register exception
	 * @param ex exception
	 * @return response entity of registration exception
	 */
	@ExceptionHandler(RegistrationException.class)
	public ResponseEntity<Response> registrationExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * purpose: This will be used when you want to throw a validation exception
	 * @param ex exception
	 * @return response entity of validation exception
	 */
	@ExceptionHandler(ValidationException.class)
	public ResponseEntity<Response> validationExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	
	/**
	 * purpose: This will be used when you want to throw a forget Password exception
	 * @param ex exception
	 * @return response entity of forget Password exception
	 */
	@ExceptionHandler(ForgetPasswordException.class)
	public ResponseEntity<Response> forgetPasswordExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose: This will be used when you want to throw when we dont get user in database
	 * @param ex exception
	 * @return response entity of forget Password exception
	 */
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Response> userNotExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	/**
	 * purpose: This will be used when you want to throw when we dont get user in database
	 * @param ex exception
	 * @return response entity of forget Password exception
	 */
	@ExceptionHandler(NotActiveException.class)
	public ResponseEntity<Response> accountNotActiveExceptionHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, ex.getMessage(), null),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(UserNotFound.class)
	public ResponseEntity<Response> userNotFoundHandler(Exception ex) {
		return new ResponseEntity<>(new Response(400, NoteMessageReference.USER_NOT_FOUND, null),HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Response> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
		return new ResponseEntity<>(new Response(400, "Firstname can not be empty", null),HttpStatus.BAD_REQUEST);
	}
}
