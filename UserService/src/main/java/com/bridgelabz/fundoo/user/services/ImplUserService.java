/******************************************************************************
*  Purpose: This class implements all methods of IUserService interface
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.UUID;

import com.bridgelabz.fundoo.user.dto.ForgetDto;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.RegisterDto;
import com.bridgelabz.fundoo.user.dto.setPasswordDto;
import com.bridgelabz.fundoo.user.exception.custom.ForgetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;
import com.bridgelabz.fundoo.user.exception.custom.NotActiveException;
import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.UserNotFoundException;
import com.bridgelabz.fundoo.user.exception.custom.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserConfig;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.utility.TokenUtility;
import com.bridgelabz.fundoo.user.utility.UserUtility;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImplUserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private MailSender mailSender;

	@Autowired
	private UserConfig config;

	@Autowired
	private UserUtility utility;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private TokenUtility tokenUtility;

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
		if (repository.findAll().stream().anyMatch(i -> i.getEmail().equals(registerDTO.getEmail()))) {
			throw new RegistrationException(StaticReference.EMAIL_ALREADY_REGISTERED);
		}
		User user = mapper.map(registerDTO, User.class);
		String token = tokenUtility.createToken(registerDTO.getEmail());
		SimpleMailMessage message = utility.getMessage(token);
		mailSender.send(message);
		repository.save(user);
		return new Response(200, StaticReference.VALIDATE_ACCOUNT, user);
	}

	/**
	 * purpose: This is service method for user login
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response loginUser(LoginDto loginDTO, String token) {
		if (!repository.findAll().stream().anyMatch(i -> i.getEmail().equals(loginDTO.getEmail())
				&& i.getEmail().equals(tokenUtility.getEmailIdFromToken(token)) || i.isIsactive()))
			throw new LoginException(StaticReference.LOGIN_FAIL);
		User user = repository.findAll().stream().filter(i -> i.getEmail().equals(loginDTO.getEmail())).findAny().get();
		String tokenUserId = Jwts.builder().setSubject(String.valueOf(user.getUId()))
				.signWith(SignatureAlgorithm.HS256, StaticReference.SECRET_KEY).compact();
		return new Response(200, StaticReference.LOGIN_SUCCESS, tokenUserId);

	}

	/**
	 * purpose: This is service method for forget Password
	 * 
	 * @param forgetDTO Data Transfer object
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response forgetPassword(ForgetDto forgetDto) {
		if(!repository.findAll().stream().filter(i->i.getEmail().equals(forgetDto.getEmail())).findAny().get().isIsactive())
			throw new NotActiveException(StaticReference.ACCOUNT_NOT_ACTIVATED);
		if (!repository.findAll().stream().anyMatch(i -> i.getEmail().equals(forgetDto.getEmail()))) {
			throw new ForgetPasswordException(StaticReference.EMAIL_NOT_FOUND);
		}
		String token = tokenUtility.createToken(forgetDto.getEmail());
		SimpleMailMessage message = utility.getMessage(token);
		message.setSubject(StaticReference.FORGET_PASSWORD_RESPONSE);
		message.setText(StaticReference.FORGET_MAIL_TEXT + token);
		mailSender.send(message);
		return new Response(200, StaticReference.FORGET_ACTION_SUCCESS, true);
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
		if(!repository.findAll().stream().filter(i->i.getEmail().equals(setPasswordDTO.getEmail())).findAny().get().isIsactive())
			throw new NotActiveException(StaticReference.ACCOUNT_NOT_ACTIVATED);
		String email = tokenUtility.getEmailIdFromToken(token);
		if (repository.findAll().stream().anyMatch(i -> i.getEmail().equals(email))) {
			User user = repository.findAll().stream().filter(i -> i.getEmail().equals(setPasswordDTO.getEmail()))
					.findAny().get();
			user.setPassword(config.passEndcode().encode(setPasswordDTO.getPassword()));
			repository.save(user);
			return new Response(200, StaticReference.SET_PASSWORD_SUCCESS, user);
		} else {
			throw new SetPasswordException(StaticReference.INVALID_LINK);
		}

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
		if (repository.findAll().stream().anyMatch(i -> i.getEmail().equals(email))) {
			User user = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().get();
			user.setIsactive(true);
			repository.save(user);
			return new Response(200, StaticReference.VERIFICATION_SUCCESS, user);
		} else {
			throw new ValidationException(StaticReference.INVALID_LINK);
		}

	}

	/**
	 * purpose: This is service method for uploading profile picture of User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response addProfile(MultipartFile file, String token) {
		String email = tokenUtility.getEmailIdFromToken(token);
		if (!repository.findAll().stream().anyMatch(i -> i.getEmail().equals(email)))
			throw new UserNotFoundException(StaticReference.EMAIL_NOT_FOUND);
		if(!repository.findAll().stream().filter(i->i.getEmail().equals(email)).findAny().get().isIsactive())
			throw new NotActiveException(StaticReference.ACCOUNT_NOT_ACTIVATED);		
		int userId = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().get().getUId();
		User user = repository.findById(userId).get();
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String fileLocation = userId + file.getOriginalFilename();
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);
			user.setProfilePicture(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.save(user);
		return new Response(200, StaticReference.PROFILE_PICTURE_SUCCESS, user);
	}

	/**
	 * purpose: This is service method for getting profile picture of User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response getProfile(String token) {
		String email = tokenUtility.getEmailIdFromToken(token);
		if (!repository.findAll().stream().anyMatch(i -> i.getEmail().equals(email)))
			throw new UserNotFoundException(StaticReference.EMAIL_NOT_FOUND);
		if(!repository.findAll().stream().filter(i->i.getEmail().equals(email)).findAny().get().isIsactive())
			throw new NotActiveException(StaticReference.ACCOUNT_NOT_ACTIVATED);
		int userId = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().get().getUId();
		return new Response(200, StaticReference.PROFILE_PICTURE_SUCCESS,
				repository.findById(userId).get().getProfilePicture());
	}

	/**
	 * purpose: This is service method for updating profile picture of User
	 * 
	 * @param token,image
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response updateProfilePic(MultipartFile file, String token) {
		String email = tokenUtility.getEmailIdFromToken(token);
		if (!repository.findAll().stream().anyMatch(i -> i.getEmail().equals(email)))
			throw new UserNotFoundException(StaticReference.EMAIL_NOT_FOUND);
		if(!repository.findAll().stream().filter(i->i.getEmail().equals(email)).findAny().get().isIsactive())
			throw new NotActiveException(StaticReference.ACCOUNT_NOT_ACTIVATED);
		int userId = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().get().getUId();
		User user = repository.findById(userId).get();
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String fileLocation = userId + file.getOriginalFilename();
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);
			user.setProfilePicture(fileLocation);
		} catch (IOException e) {
			e.printStackTrace();
		}
		repository.save(user);
		return new Response(200, StaticReference.PROFILE_PICTURE_UPDATION_SUCCESS, user);
	}

	@Override
	public Response deleteProfilePic(String token) {
		String email = tokenUtility.getEmailIdFromToken(token);
		User user = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().get();
		if (!repository.findAll().stream().anyMatch(i -> i.getEmail().equals(email)))
			throw new UserNotFoundException(StaticReference.EMAIL_NOT_FOUND);
		if(!repository.findAll().stream().filter(i->i.getEmail().equals(email)).findAny().get().isIsactive())
			throw new NotActiveException(StaticReference.ACCOUNT_NOT_ACTIVATED);
		String fileLocation = user.getProfilePicture();
		File file = new File(fileLocation);
		file.delete();
		user.setProfilePicture(null);
		repository.save(user);
		return new Response(200, StaticReference.PROFILE_PICTURE_DELETE_SUCCESS, true);

	}
}
