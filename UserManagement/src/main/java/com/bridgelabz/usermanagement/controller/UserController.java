package com.bridgelabz.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.usermanagement.dto.CreateUserDto;
import com.bridgelabz.usermanagement.dto.LoginDto;
import com.bridgelabz.usermanagement.response.Response;
import com.bridgelabz.usermanagement.service.ImplUserService;

@RestController
public class UserController {
	
	@Autowired
	private ImplUserService service;

	@PostMapping
	public Response registerUser(@RequestBody CreateUserDto createUserDto) {
		return service.registerAdmin(createUserDto);
	}
	@PostMapping("/login")
	public Response loginUser(@RequestBody LoginDto loginDto) {
		return service.login(loginDto);
	}
}
