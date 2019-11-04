/******************************************************************************
*  Purpose: This class is Controller class
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.user.dto.ForgetDto;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.RegisterDto;
import com.bridgelabz.fundoo.user.dto.setPasswordDto;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.services.ImplUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImplUserService.class);

	@Autowired
	ImplUserService service;

	/**
	 * purpose: This method will login if you have entered right credentials else it
	 * will throw exception
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	@PutMapping("/login")
	public ResponseEntity<Response> loginUser(@RequestBody LoginDto loginDto,@RequestHeader String token) {

		LOGGER.info("login logger");
		return new ResponseEntity<Response>(service.loginUser(loginDto,token), HttpStatus.OK);
	}

	/**
	 * purpose: This method will send an email to your mail Id for verification if
	 * you have entered right credentials else it will throw exception
	 * 
	 * @param registerDto Data Transfer object while registering
	 * 
	 * @return Response to your action
	 */

	@PostMapping("/register")
	public ResponseEntity<Response> registerUser(@RequestBody RegisterDto registerDto) {

		LOGGER.info("registration logger");
		return new ResponseEntity<Response>(service.registerUser(registerDto), HttpStatus.OK);
	}

	/**
	 * purpose: This method will take email as input and sends a mail to your
	 * registered emailId for resetting your password
	 * 
	 * @param forgetDto Data Transfer object
	 * 
	 * @return Response to your action
	 */
	@PutMapping("/forgetpassword")
	public ResponseEntity<Response> forgetPassword(@RequestBody ForgetDto forgetDto) {
		LOGGER.info("forgetPassword logger");
		return new ResponseEntity<Response>(service.forgetPassword(forgetDto), HttpStatus.OK);

	}

	/**
	 * purpose: This method will Change your password if you have gone on right link
	 * 
	 * @param setPasswordDto Data Transfer object while registering, token
	 * 
	 * @return true if you are registered after putting appropriate values
	 */
	@PutMapping("/resetpassword")
	public ResponseEntity<Response> setPassword(@RequestBody setPasswordDto setPasswordDto,
			@RequestHeader String token) {
		LOGGER.info("resetPassword logger");
		return new ResponseEntity<Response>(service.setPassword(setPasswordDto, token), HttpStatus.OK);

	}

	/**
	 * purpose: This method will validate your account if you have gone on right link
	 * 
	 * @param setPasswordDto Data Transfer object while registering, token
	 * 
	 * @return true if you are registered after putting appropriate values
	 */
	@PutMapping("/validate")
	public ResponseEntity<Response> validateUser(@RequestHeader String token) {
		LOGGER.info("validate email logger");
		return new ResponseEntity<Response>(service.validateUser(token), HttpStatus.OK);

	}
	
	/**
	 * purpose: This is method for uploading profile picture of User
	 * 
	 * @param token,path of file
	 * 
	 * @return Response to your action
	 */
	@PutMapping("/addprofile")
	public ResponseEntity<Response> addProfileOfUser(@RequestBody MultipartFile file,@RequestHeader String token) {
		LOGGER.info("validate email logger");
		return new ResponseEntity<Response>(service.addProfile(file,token), HttpStatus.OK);

	} 
	/**
	 * purpose: This is method for getting profile picture of User
	 * 
	 * @param token,path of file
	 * 
	 * @return Response to your action
	 */
	@GetMapping("/getprofile")
	public ResponseEntity<Response> getProfileOfUser(@RequestHeader String token) {
		return new ResponseEntity<Response>(service.getProfile(token), HttpStatus.OK);

	} 
	
	/**
	 * purpose: This is service method for updating profile picture of User
	 * 
	 * @param token,image
	 * 
	 * @return Response to your action
	 */
	@PutMapping("/updateprofile")
	public ResponseEntity<Response> updateProfileOfUser(@RequestBody MultipartFile file,@RequestHeader String token) throws IOException {
		return new ResponseEntity<Response>(service.updateProfilePic(file,token), HttpStatus.OK);

	} 
	
	/**
	 * purpose: This is service method for deleting profile picture of User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	@DeleteMapping("/deleteprofile")
	public ResponseEntity<Response> deleteProfileOfUser(@RequestHeader String token) throws IOException {
		return new ResponseEntity<Response>(service.deleteProfilePic(token), HttpStatus.OK);

	} 
	

}
