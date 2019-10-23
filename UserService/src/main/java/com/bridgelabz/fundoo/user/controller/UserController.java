package com.bridgelabz.fundoo.user.controller;

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
import com.bridgelabz.fundoo.user.services.ImplUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ImplUserService.class);

	@Autowired
	ImplUserService service;

	@PostMapping("/login")
	public boolean loginUser(@RequestBody LoginDTO loginDTO) {

		LOGGER.info("login logger");
		return service.loginUser(loginDTO);
	}

	@PostMapping("/register")
	public void registerUser(@RequestBody RegisterDTO registerDTO) {

		LOGGER.info("registration logger");
		service.registerUser(registerDTO);
	}

	@PostMapping("/fogetPassword")
	public void forgetPassword(@RequestBody ForgetDTO forgetDTO) {
		LOGGER.info("forget Password logger");
		service.forgetPassword(forgetDTO);

	}

	@PutMapping("/setpassword/{token}")
	public void setPassword(@RequestBody setPasswordDTO setPasswordDTO, @PathVariable String token) {
		LOGGER.info("set Password logger");
		service.setPassword(setPasswordDTO, token);

	}

	@PutMapping("/validate/{token}")
	public void validateUser(@PathVariable String token) {
		LOGGER.info("validate email logger");
		service.validateUser(token);

	}

}
