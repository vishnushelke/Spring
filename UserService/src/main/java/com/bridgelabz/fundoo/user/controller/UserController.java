package com.bridgelabz.fundoo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.ForgetDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.service.ImplUserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private ImplUserService service;

	@PutMapping("/login")
	public boolean loginUser(@RequestBody LoginDTO loginDTO) {
		return service.loginUser(loginDTO);
	}

	@PostMapping("/register")
	public User registerUser(@RequestBody RegisterDTO registerDTO) {
		System.out.println(registerDTO);
		return service.registerUser(registerDTO);
	}

	@PostMapping("/forget")
	public User forgetPassword(@RequestBody ForgetDTO forgetDTO) {
		return service.forgetPassword(forgetDTO);
	}

	@PutMapping("/setPassword/{token}")
	public void setPassword(@RequestBody SetPasswordDTO setPasswordDTO, @PathVariable(name = "token") String token) {
		service.setPassword(setPasswordDTO);
	}
}
