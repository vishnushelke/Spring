package com.bridgelabz.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.usermanagement.dto.CreateUserDto;
import com.bridgelabz.usermanagement.dto.LoginDto;
import com.bridgelabz.usermanagement.dto.UpdateUserDto;
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
	@PostMapping("/forget")
	public Response forgetPassword(@RequestParam String email) {
		return service.forgotPassword(email);
	}
	@PutMapping("/update")
	public Response updateUser(@RequestParam int userId,@RequestBody UpdateUserDto updateUserDto, @RequestHeader String token) {
		return service.updateUser(userId,updateUserDto,token);
	}
}
