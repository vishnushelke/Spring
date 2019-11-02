/******************************************************************************
*  Purpose: This class contains all references in our program
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/

package com.bridgelabz.fundoo.user.services;

public class StaticReference {
	
	//user related messages
	public static final String LOGIN_SUCCESS = "Logged in successfully";
	
	public static final String LOGIN_FAIL = "Email id or password you have entered is wrong";
	
	
	
	public static final String VALIDATE_ACCOUNT = "Verification link is sent to your email id";
	
	public static final String EMAIL_ALREADY_REGISTERED = "Email id already Registered";
	
	
	
	public static final String FORGET_ACTION_SUCCESS = "Link sent to your mail id to reset your password";
	
	public static final String EMAIL_NOT_FOUND = "Email you entered is not registered yet";
	
	
	public static final String SET_PASSWORD_SUCCESS = "Password is set successfully";
	
	public static final String INVALID_LINK = "This is an invalid link";
	
	public static final String VERIFICATION_SUCCESS = "Registered your account successfully";
	
	public static final String REGISTRATION_RESPONSE = "response to your registration";
	
	public static final String FORGET_PASSWORD_RESPONSE = "response to your forget password";
	
	public static final String PROFILE_PICTURE_SUCCESS = "Profile picture is set successfully";
	
	public static final String PROFILE_PICTURE_FETCH_SUCCESS = "Profile picture is fetched successfully";
	
	
	//keys
	public static final String SECRET_KEY = "secretKey";
	
	//url
	public static final String REGISTRATION_MAIL_TEXT ="validate your email\n"+"http://localhost:8080/user/validate/";
	
	public static final String FORGET_MAIL_TEXT ="Click here to reset your password\n"+"http://localhost:8080/user/resetpassword/";
	
	
}
