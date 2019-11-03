/******************************************************************************
*  Purpose: This class implements all methods of IUserService interface
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.services;

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
import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.ValidationException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

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
	private UUID uuid;

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
			message.setText(StaticReference.REGISTRATION_MAIL_TEXT + token);
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
			User user = repository.findAll().stream().filter(i -> i.getEmail().equals(loginDTO.getEmail())).findAny()
					.get();
			String token = Jwts.builder().setSubject(String.valueOf(user.getUId()))
					.signWith(SignatureAlgorithm.HS256, StaticReference.SECRET_KEY).compact();
			return new Response(200, StaticReference.LOGIN_SUCCESS, token);
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
			message.setText(StaticReference.FORGET_MAIL_TEXT + token);
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
	 * purpose: This method will check if email is registered or not and gives that
	 * user object
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

	/**
	 * purpose: This is service method for uploading profile picture of User
	 * 
	 * @param token
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response addProfile(String path, String token) {
		// TODO Auto-generated method stub
		
		Claims claim = Jwts.parser().setSigningKey(StaticReference.SECRET_KEY).parseClaimsJws(token).getBody();
		int userId = Integer.parseInt(claim.getSubject());
		
		User user = repository.findAll().stream().filter(i -> i.getUId() == userId).findAny().get();
		user.setProfilePicture(path);
		repository.save(user);
		return new Response(200, StaticReference.PROFILE_PICTURE_SUCCESS, user);
	}

	@Override
	public Response getProfile(String token) {
		// TODO Auto-generated method stub
		Claims claim = Jwts.parser().setSigningKey(StaticReference.SECRET_KEY).parseClaimsJws(token).getBody();
		int userId = Integer.parseInt(claim.getSubject());
		return new Response(200, StaticReference.PROFILE_PICTURE_SUCCESS,
				repository.findById(userId).get().getProfilePicture());
	}

	public String upload(Model model, MultipartFile[] files) {
		StringBuilder fileNames = new StringBuilder();
		for (MultipartFile file : files) {
			Path fileNameAndPath = Paths.get(System.getProperty("user.dir" + "/uploads"), file.getOriginalFilename());
			fileNames.append(file.getOriginalFilename() + " ");
			try {
				Files.write(fileNameAndPath, file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		model.addAttribute("msg", "Successfully uploaded files " + fileNames.toString());
		return "uploadstatusview";
	}

}
