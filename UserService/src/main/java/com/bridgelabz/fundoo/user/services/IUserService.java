/******************************************************************************
*  Purpose: This is an interface which has all methods which we want to implement
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.services;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.user.dto.ForgetDto;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.RegisterDto;
import com.bridgelabz.fundoo.user.dto.SetPasswordDto;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.response.ResponseLogin;

public interface IUserService {
	
	/**
	 * purpose: This is service method for user registration
	 * 
	 * @param registerDTO Data Transfer object while registering
	 * 
	 * @return Response to your action
	 */
	public Response registerUser(RegisterDto registerDTO);
	
	/**
	 * purpose: This is service method for user login
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	public ResponseLogin loginUser(LoginDto registerDTO);
	
	/**
	 * purpose: This is service method for forget Password
	 * 
	 * @param forgetDTO Data Transfer object
	 * 
	 * @return Response to your action
	 */
	public Response forgetPassword(ForgetDto forgetDto);
	
	/**
	 * purpose: This is service method for resetting Password
	 * 
	 * @param setPasswordDto Data Transfer object,token
	 * 
	 * @return Response to your action
	 */
	public Response setPassword(SetPasswordDto setPasswordDto,String token);
	
	/**
	 * purpose: This is service method for validating User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	public Response validateUser(String token);

	/**
	 * purpose: This is service method for uploading profile picture of User
	 * 
	 * @param token,image
	 * 
	 * @return Response to your action
	 */
	public Response addProfile(MultipartFile file,String token);
	
	/**
	 * purpose: This is service method for getting profile picture of User
	 * 
	 * @param token,image
	 * 
	 * @return Response to your action
	 */
	public Response getProfile(String token);
	
	/**
	 * purpose: This is service method for updating profile picture of User
	 * 
	 * @param token,image
	 * 
	 * @return Response to your action
	 */
	public Response updateProfilePic(MultipartFile file, String token);
	
	/**
	 * purpose: This is service method for deleting profile picture of User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	public Response deleteProfilePic(String token);
}
