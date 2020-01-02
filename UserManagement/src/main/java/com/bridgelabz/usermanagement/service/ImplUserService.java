package com.bridgelabz.usermanagement.service;

import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.configuration.RabbitMQBody;
import com.bridgelabz.usermanagement.configuration.UserConfig;
import com.bridgelabz.usermanagement.dto.CreateUserDto;
import com.bridgelabz.usermanagement.dto.LoginDto;
import com.bridgelabz.usermanagement.dto.UpdateUserDto;
import com.bridgelabz.usermanagement.dto.UpdateWebpagePermission;
import com.bridgelabz.usermanagement.exception.custom.NotAuthorizedException;
import com.bridgelabz.usermanagement.exception.custom.UserAlreadyAvailableException;
import com.bridgelabz.usermanagement.exception.custom.UserNameAlreadyAvailableException;
import com.bridgelabz.usermanagement.exception.custom.UserNotFoundException;
import com.bridgelabz.usermanagement.exception.custom.UserNotVerifiedException;
import com.bridgelabz.usermanagement.model.User;
import com.bridgelabz.usermanagement.repository.UserRepository;
import com.bridgelabz.usermanagement.response.Response;
import com.bridgelabz.usermanagement.utility.TokenUtility;
import com.bridgelabz.usermanagement.utility.Utility;

@Service
public class ImplUserService implements IUserService {

	@Autowired
	private UserRepository repository;

	@Autowired
	private UserConfig config;

	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private TokenUtility tokenUtility;
	
	@Autowired
	private Utility utility;
	
	@Autowired
	private RabbitTemplate template;

	@Override
	public Response registerAdmin(CreateUserDto createUserDto) {
		if (!createUserDto.getUserRole().equalsIgnoreCase("admin"))
			throw new NotAuthorizedException();
		if (repository.findAll().stream().anyMatch(i -> i.getEmailId().equals(createUserDto.getEmailId())))
			throw new UserAlreadyAvailableException();
		User user = mapper.map(createUserDto, User.class);
		user.setPassword(config.getPasswordEncoder().encode(createUserDto.getPassword()));
		user.setStatus(true);
		repository.save(user);
		RabbitMQBody body = utility.getRabbitMqBody(tokenUtility.createToken(user.getUId()), user.getEmailId(),"click to verify\nhttp://localhost:8080/verify/");
		template.convertAndSend("userMessageQueue", body);
		return new Response(200, "admin Registered successfully", user);
	}

	@Override
	public Response login(LoginDto loginDto) {
		User user = repository.findAll().stream().filter(i -> i.getUserName().equals(loginDto.getUserName())).findAny()
				.orElseThrow(UserNotFoundException::new);
		if(!user.isStatus())
			throw new UserNotVerifiedException();
		if (!config.getPasswordEncoder().matches(loginDto.getPassword(), user.getPassword()))
			return new Response(400, "Wrong Password", false);
		return new Response(200, "Login Success", tokenUtility.createToken(user.getUId()));
	}

	@Override
	public Response createUser(CreateUserDto createUserDto,String token) {
		int userId = tokenUtility.getIdFromToken(token);
		User admin = repository.findById(userId).orElseThrow(NotAuthorizedException::new);
		System.out.println(admin.toString());
		if(!admin.getUserRole().equalsIgnoreCase("admin"))
			throw new NotAuthorizedException();	
		if (repository.findAll().stream().anyMatch(i -> i.getEmailId().equals(createUserDto.getEmailId())))
			throw new UserAlreadyAvailableException();
		if(repository.findAll().stream().anyMatch(i->i.getUserName().equalsIgnoreCase(createUserDto.getUserName())))
			throw new UserNameAlreadyAvailableException();
		User user = mapper.map(createUserDto, User.class);
		user.setPassword(config.getPasswordEncoder().encode(createUserDto.getPassword()));
		repository.save(user);
		return new Response(200, "User created successfully", user);		
	}

	@Override
	public Response validateUser(String token) {
		int userId = tokenUtility.getIdFromToken(token);
		User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
		user.setStatus(true);
		repository.save(user);
		return new Response(200, "Validation Success", true);
	}

	@Override
	public Response forgotPassword(String email) {
		User user = repository.findByEmailId(email).orElseThrow(UserNotFoundException::new);
		if(!user.isStatus())
			throw new UserNotVerifiedException();
		RabbitMQBody body = utility.getRabbitMqBody(tokenUtility.createToken(user.getUId()), user.getEmailId(),"click to verify\nhttp://localhost:8080/reset/");		
		template.convertAndSend("userMessageQueue", body);
		return new Response(200, "Link Sent to registered email", user);
	}

	@Override
	public Response updateUser(int userId,UpdateUserDto updateUserDto,String token) {
		User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
		if(!(userId==tokenUtility.getIdFromToken(token) || user.getUserRole().equalsIgnoreCase("admin")))
			throw new NotAuthorizedException();		
		user.setFirstName(updateUserDto.getFirstName());
		user.setCountry(updateUserDto.getCountry());
		user.setMiddleName(updateUserDto.getMiddleName());
		user.setLastName(updateUserDto.getLastName());
		user.setGender(updateUserDto.getGender());
		user.setCountry(updateUserDto.getCountry());
		user.setAddress(updateUserDto.getAddress());
		user.setPassword(config.getPasswordEncoder().encode(updateUserDto.getPassword()));
		if(user.getUserRole().equalsIgnoreCase("admin")) {
			user.setEmailId(updateUserDto.getEmailId());
			user.setUserName(updateUserDto.getUserName());
			user.setUserRole(updateUserDto.getUserRole());
		}
		repository.save(user);
		return new Response(200, "User Updated SuccessFully", user);
	}

	@Override
	public Response deleteUser(int userId,String token) {
		User admin = repository.findById(tokenUtility.getIdFromToken(token)).orElseThrow(NotAuthorizedException::new);
		User user = repository.findById(userId).orElseThrow(UserNotFoundException::new);
		if(!admin.getUserRole().equalsIgnoreCase("admin"))
			throw new NotAuthorizedException();
		repository.delete(user);
		return new Response(200, "User deleted SuccessFully", user);
	}

	@Override
	public Response updateProfilePic(String token, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response clearProfilePic(String token, MultipartFile file) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getLoginHistory(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAlltimeData(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getThisYearData(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getThisMonthData(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getAllUsers(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getActiveUsers(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response getInactiveUsers(String token) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response changeWebPagePermission(String token, int userId, UpdateWebpagePermission permission) {
		// TODO Auto-generated method stub
		return null;
	}

}
