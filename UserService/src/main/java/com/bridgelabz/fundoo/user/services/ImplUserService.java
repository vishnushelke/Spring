/******************************************************************************
*  Purpose: This class implements all methods of IUserService interface
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.services;

import java.util.Date;
import com.bridgelabz.fundoo.user.dto.ForgetDto;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.RegisterDto;
import com.bridgelabz.fundoo.user.dto.setPasswordDto;
import com.bridgelabz.fundoo.user.exception.custom.ForgetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserConfig;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.utility.UserUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImplUserService implements IUserService {

	@Autowired
	UserRepository repository;

	@Autowired
	MailSender mailSender;

	@Autowired
	UserConfig config;

	@Autowired
	UserUtility utility;

	@Autowired
	ModelMapper mapper;
	
	/**
	 * purpose: This is service method for user registration
	 * 
	 * @param registerDTO Data Transfer object while registering
	 * 
	 * @return Response to your action
	 */


	@Override
	public Response registerUser(RegisterDto registerDTO) {
		registerDTO.setPassword(config.passEndcode().encode(registerDTO.getPassword()));
		if (!alreadyAvailable(registerDTO.getEmail())) {
			User user = mapper.map(registerDTO, User.class);
			String token = Jwts.builder().setSubject(registerDTO.getEmail()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, StaticReference.SECRET_KEY).compact();
			SimpleMailMessage message = utility.getMessage();
			System.out.println(message.getFrom());
			message.setSubject(StaticReference.REGISTRATION_RESPONSE);
			message.setText(StaticReference.REGISTRATION_MAIL_TEXT+ token);
			mailSender.send(message);
			repository.save(user);

			return new Response(200, StaticReference.VALIDATE_ACCOUNT, user);

		} else {
			throw new RegistrationException(StaticReference.EMAIL_ALREADY_REGISTERED);
		}

	}

	/**
	 * purpose: This is service method for user login
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response loginUser(LoginDto loginDTO) {
		System.out.println(loginDTO.getEmail() + " " + loginDTO.getPassword().length());
		if (!(loginDTO.getEmail().equals(null) || loginDTO.getPassword().length() < 6)) {
			repository.findAll().stream().anyMatch(t -> t.getEmail().equals(loginDTO.getEmail())
					&& config.passEndcode().matches(loginDTO.getPassword(), t.getPassword()));
			return new Response(200, StaticReference.LOGIN_SUCCESS, true);
		} else {
			throw new LoginException(StaticReference.LOGIN_FAIL);
		}

	}

	/**
	 * purpose: This is service method for forget Password
	 * 
	 * @param forgetDTO Data Transfer object
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response forgetPassword(ForgetDto forgetDTO) {
		if (alreadyAvailable(forgetDTO.getEmail())) {

			String token = Jwts.builder().setSubject(forgetDTO.getEmail()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, StaticReference.SECRET_KEY).compact();
			SimpleMailMessage message = utility.getMessage();
			message.setSubject(StaticReference.FORGET_PASSWORD_RESPONSE);
			message.setText(StaticReference.FORGET_MAIL_TEXT +token);
			mailSender.send(message);
			return new Response(200, StaticReference.FORGET_ACTION_SUCCESS, true);
		} else {
			throw new ForgetPasswordException(StaticReference.EMAIL_NOT_FOUND);
		}
	}

	/**
	 * purpose: This is service method for resetting Password
	 * 
	 * @param setPasswordDTO Data Transfer object,token
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response setPassword(setPasswordDto setPasswordDTO, String token) {
		Claims claim = Jwts.parser().setSigningKey(StaticReference.SECRET_KEY).parseClaimsJws(token).getBody();
		String email = claim.getSubject();
		if (alreadyAvailable(email)) {
			User user = alreadyAvailableUser(email);
			user.setPassword(config.passEndcode().encode(setPasswordDTO.getPassword()));
			repository.save(user);
			return new Response(200, StaticReference.SET_PASSWORD_SUCCESS, user);
		} else {
			throw new SetPasswordException(StaticReference.INVALID_LINK);
		}

	}

	/**
	 * purpose: This method will check if email is registered or not
	 * 
	 * @param emailId
	 * 
	 * @return returns boolean value,true if found in database
	 */
	public boolean alreadyAvailable(String email) {
		System.out.println(email);
		return repository.findAll().stream().anyMatch(t -> t.getEmail().equals(email));
	}

	/**
	 * purpose: This method will check if email is registered or not and gives that user object
	 * 
	 * @param emailId
	 * 
	 * @return returns user object in database
	 */
	public User alreadyAvailableUser(String email) {
		System.out.println(email);
		return repository.findAll().stream().findAny().filter(t -> t.getEmail().equals(email)).get();
	}

	/**
	 * purpose: This is service method for validating User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response validateUser(String token) {
		Claims claim = Jwts.parser().setSigningKey(StaticReference.SECRET_KEY).parseClaimsJws(token).getBody();
		String email = claim.getSubject();
		if (alreadyAvailable(email)) {
			User user = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().orElse(null);
			user.setIsactive(true);
			repository.save(user);
			return new Response(200, StaticReference.VERIFICATION_SUCCESS, user);
		} else {
			throw new ValidationException(StaticReference.INVALID_LINK);
		}

	}

}
