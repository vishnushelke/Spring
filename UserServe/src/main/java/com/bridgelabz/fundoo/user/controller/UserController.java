package com.bridgelabz.fundoo.user.controller;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.bridgelabz.fundoo.user.DTO.ForgetDTO;
import com.bridgelabz.fundoo.user.DTO.LoginDTO;
import com.bridgelabz.fundoo.user.DTO.RegisterDTO;
import com.bridgelabz.fundoo.user.DTO.setPasswordDTO;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.services.ImplUserService;

import io.jsonwebtoken.Jwts;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository repository;

	@Autowired
	ModelMapper mapper;

	@Autowired
	ImplUserService service;

	@PostMapping("/login")
	public boolean loginUser(@RequestBody LoginDTO loginDTO) {
		LOG.info("register API");
		return service.loginUser(loginDTO);
	}

	@PostMapping("/register")
	public void registerUser(@RequestBody RegisterDTO registerDTO) {
		LOG.info("register API");
		service.registerUser(registerDTO);
	}

	@PostMapping("/fogetPassword")
	public void forgetPassword(@RequestBody ForgetDTO forgetDTO) {
		service.forgetPassword(forgetDTO);
	}

	@PutMapping("/setpassword/{token}")
	public void setPassword(@RequestBody setPasswordDTO setPasswordDTO,@PathVariable String token) {
		service.setPassword(setPasswordDTO,token);
	}
	
	@PutMapping("/validate/{token}")
	public void validateUser(@PathVariable String token)
	{
		service.validateUser(token);
	}

}
