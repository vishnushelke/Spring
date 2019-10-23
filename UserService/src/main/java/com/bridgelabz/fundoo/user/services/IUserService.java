/******************************************************************************
*  Purpose: This is an interface which has all methods which we want to implement
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.services;

import com.bridgelabz.fundoo.user.dto.ForgetDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.setPasswordDTO;
import com.bridgelabz.fundoo.user.response.Response;

public interface IUserService {
	
	/**
	 * purpose This is service method for user registration
	 * 
	 * @param registerDTO Data Transfer object while registering
	 * 
	 * @return Response to your action
	 */
	public Response registerUser(RegisterDTO registerDTO);
	
	/**
	 * purpose This is service method for user login
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	public Response loginUser(LoginDTO registerDTO);
	
	/**
	 * purpose This is service method for forget Password
	 * 
	 * @param forgetDTO Data Transfer object
	 * 
	 * @return Response to your action
	 */
	public Response forgetPassword(ForgetDTO registerDTO);
	
	/**
	 * purpose This is service method for resetting Password
	 * 
	 * @param setPasswordDTO Data Transfer object,token
	 * 
	 * @return Response to your action
	 */
	public Response setPassword(setPasswordDTO registerDTO,String token);
	
	/**
	 * purpose This is service method for validating User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	public Response validateUser(String token);

}
