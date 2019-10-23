/******************************************************************************
*  Purpose: This class is Controller class
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.user.dto.ForgetDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.setPasswordDTO;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.services.ImplUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImplUserService.class);

	@Autowired
	ImplUserService service;

	/**
	 * purpose This method will login if you have entered right credentials else it
	 * will throw exception
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	@PutMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDTO loginDTO) {

		LOGGER.info("login logger");
		return new ResponseEntity<Response>(service.loginUser(loginDTO), HttpStatus.OK);
	}

	/**
	 * purpose This method will send an email to your mail Id for verification if
	 * you have entered right credentials else it will throw exception
	 * 
	 * @param registerDTO Data Transfer object while registering
	 * 
	 * @return Response to your action
	 */

	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@RequestBody RegisterDTO registerDTO) {

		LOGGER.info("registration logger");
		return new ResponseEntity<Response>(service.registerUser(registerDTO), HttpStatus.OK);
	}

	/**
	 * purpose This method will take email as input and sends a mail to your
	 * registered emailId for resetting your password
	 * 
	 * @param forgetDTO Data Transfer object
	 * 
	 * @return Response to your action
	 */
	@PutMapping("/forgetpassword")
	public ResponseEntity<Response> forgetPassword(@RequestBody ForgetDTO forgetDTO) {
		LOGGER.info("forgetPassword logger");
		return new ResponseEntity<Response>(service.forgetPassword(forgetDTO), HttpStatus.OK);

	}

	/**
	 * purpose This method will Change your password if you have gone on right link
	 * 
	 * @param setPasswordDTO Data Transfer object while registering, token
	 * 
	 * @return true if you are registered after putting appropriate values
	 */
	@PutMapping("/resetpassword")
	public ResponseEntity<Response> setPassword(@RequestBody setPasswordDTO setPasswordDTO,
			@RequestHeader String token) {
		LOGGER.info("resetPassword logger");
		return new ResponseEntity<Response>(service.setPassword(setPasswordDTO, token), HttpStatus.OK);

	}

	/**
	 * purpose This method will validate your account if you have gone on right link
	 * 
	 * @param setPasswordDTO Data Transfer object while registering, token
	 * 
	 * @return true if you are registered after putting appropriate values
	 */
	@PutMapping("/validate")
	public ResponseEntity<Response> validateUser(@RequestHeader String token) {
		LOGGER.info("validate email logger");
		return new ResponseEntity<Response>(service.validateUser(token), HttpStatus.OK);

	}

}
