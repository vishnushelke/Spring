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
import java.nio.file.StandardCopyOption;
import java.util.Map;

import com.bridgelabz.fundoo.user.dto.ForgetDto;
import com.bridgelabz.fundoo.user.dto.LoginDto;
import com.bridgelabz.fundoo.user.dto.RegisterDto;
import com.bridgelabz.fundoo.user.dto.SetPasswordDto;
import com.bridgelabz.fundoo.user.exception.custom.NotActiveException;
import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.UserNotFound;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.fundoo.user.model.RabbitMQBody;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserConfig;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.response.Response;
import com.bridgelabz.fundoo.user.response.ResponseLogin;
import com.bridgelabz.fundoo.user.utility.TokenUtility;
import com.bridgelabz.fundoo.user.utility.UserUtility;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

@Service
public class ImplUserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserConfig config;

	@Autowired
	private UserUtility utility;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private RabbitTemplate template;

	@Autowired
	private TokenUtility tokenUtility;

	private final String path = "/home/user/Desktop/vishnu/spring-tool-suite-4-4.4.0.RELEASE-e4.13.0-linux.gtk.x86_64/spring programs/Spring/UserService/uploads/";

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
			throw new RegistrationException(MessageReference.EMAIL_ALREADY_REGISTERED);
		}
		User user = mapper.map(registerDTO, User.class);
		repository.save(user);
		String token = tokenUtility.createToken(user.getUId());
		RabbitMQBody body = utility.getRabbitBody(token, registerDTO.getEmail());
		template.convertAndSend("userMessageQueue", body);
		return new Response(200, MessageReference.VALIDATE_ACCOUNT, user);
	}

	/**
	 * purpose: This is service method for user login
	 * 
	 * @param loginDTO Data Transfer object while logging in
	 * 
	 * @return Response to your action
	 */
	@Override
	public ResponseLogin loginUser(LoginDto loginDTO) {
		User user = repository.findByEmail(loginDTO.getEmail()).orElseThrow(UserNotFound::new);
		if (!config.passEndcode().matches(loginDTO.getPassword(), user.getPassword())) {
			return new ResponseLogin(200, MessageReference.LOGIN_FAIL, false, null);
		}
		String token = tokenUtility.createToken(user.getUId());
		if (!user.isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		return new ResponseLogin(200, MessageReference.LOGIN_SUCCESS, token, user);
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
		User user = repository.findByEmail(forgetDto.getEmail()).orElseThrow(UserNotFound::new);
		if (!user.isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		String token = tokenUtility.createToken(user.getUId());
		RabbitMQBody body = utility.getRabbitBody(token, forgetDto.getEmail());
		body.setSubject(MessageReference.FORGET_PASSWORD_RESPONSE);
		body.setBody(MessageReference.FORGET_MAIL_TEXT + token);
		template.convertAndSend("userMessageQueue", body);
		return new Response(200, MessageReference.FORGET_ACTION_SUCCESS, true);
	}

	/**
	 * purpose: This is service method for resetting Password
	 * 
	 * @param setPasswordDTO Data Transfer object,token
	 * 
	 * @return Response to your action
	 */
	@Override
	public Response setPassword(SetPasswordDto setPasswordDTO, String token) {
		int userId = tokenUtility.getUserIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFound::new);
		if (!user.isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		user.setPassword(config.passEndcode().encode(setPasswordDTO.getPassword()));
		repository.save(user);
		return new Response(200, MessageReference.SET_PASSWORD_SUCCESS, user);
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
		int userId = tokenUtility.getUserIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFound::new);
		user.setIsactive(true);
		repository.save(user);
		return new Response(200, MessageReference.VERIFICATION_SUCCESS, user);
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
		int userId = tokenUtility.getUserIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFound::new);
		if (!user.isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap("cloud_name", "dx7rdnzgv", "api_key",
				"876782983213561", "api_secret", "EWJ4R0smSMSedWXWBi_0vJDVWE0"));
		// Normalize file name
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		fileName = userId + fileName;
		try {
			// Copy file to the target location (Replacing existing file with the same name)
			Path getPath = Paths.get(path);
			Path targetLocation = getPath.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
			// upload on cloudinary
			File toUpload = new File(targetLocation.toString());
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
			user.setProfilePicture(uploadResult.get("secure_url").toString());
			repository.save(user);
			return new Response(200, "Profile pic uploaded", uploadResult.get("secure_url").toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return new Response(200, MessageReference.PROFILE_PICTURE_SUCCESS, user);
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
		int userId = tokenUtility.getUserIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFound::new);
		if (!user.isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		return new Response(200, MessageReference.PROFILE_PICTURE_FETCH_SUCCESS, user.getProfilePicture());
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
		int userId = tokenUtility.getUserIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFound::new);
		if (!repository.findById(userId).get().isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		byte[] bytes;
		try {
			bytes = file.getBytes();
			String fileLocation = userId + file.getOriginalFilename();
			Path path = Paths.get(fileLocation);
			Files.write(path, bytes);
			user.setProfilePicture(fileLocation);
			repository.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Response(200, MessageReference.PROFILE_PICTURE_UPDATION_SUCCESS, user);
	}

	@Override
	public Response deleteProfilePic(String token) {
		int userId = tokenUtility.getUserIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFound::new);
		if (!repository.findById(userId).get().isIsactive())
			throw new NotActiveException(MessageReference.ACCOUNT_NOT_ACTIVATED);
		String fileLocation = user.getProfilePicture();
		File file = new File(fileLocation);
		file.delete();
		user.setProfilePicture(null);
		repository.save(user);
		return new Response(200, MessageReference.PROFILE_PICTURE_DELETE_SUCCESS, true);

	}

}
