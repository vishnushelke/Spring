package com.bridgelabz.usermanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.dto.CreateUserDto;
import com.bridgelabz.usermanagement.dto.LoginDto;
import com.bridgelabz.usermanagement.dto.UpdateUserDto;
import com.bridgelabz.usermanagement.dto.UpdateWebpagePermission;
import com.bridgelabz.usermanagement.response.Response;
import com.bridgelabz.usermanagement.service.ImplUserService;

@RestController
public class UserController {
	
	@Autowired
	private ImplUserService service;

	@PostMapping
	public Response registerAdmin(@RequestBody CreateUserDto createUserDto) {
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
	@PutMapping("/verify")
	public Response verifyUser(@RequestHeader String token) {
		return service.validateUser(token);
	}
	@DeleteMapping
	public Response deleteUser(@RequestParam int userId,@RequestHeader String token) {
		return service.deleteUser(userId, token);
	}
	@PostMapping("/createuser")
	public Response createUser(@RequestBody CreateUserDto createUserDto, @RequestHeader String token) {
		return service.createUser(createUserDto, token);
	}
	@PutMapping("/updateprofile")
	public Response updateUserProfile(@RequestParam int userId,@RequestBody MultipartFile file, @RequestHeader String token) {
		return service.updateProfilePic(userId, token, file);
	}
	@PutMapping("/clearprofile")
	public Response clearUserProfile(@RequestParam int userId,@RequestBody MultipartFile file, @RequestHeader String token) {
		return service.clearProfilePic(userId, token, file);
	}
	@GetMapping("/loginhistory")
	public Response getLoginHistory(@RequestParam int userId, @RequestHeader String token) {
		return service.getLoginHistory(userId, token);
	}
	@GetMapping("/activeusers")
	public Response getActiveUsers(@RequestHeader String token) {
		return service.getActiveUsers(token);
	}
	@GetMapping("/inactiveusers")
	public Response getInactiveUsers(@RequestHeader String token) {
		return service.getInactiveUsers(token);
	}
	@GetMapping("/allusers")
	public Response getAllUsers(@RequestHeader String token) {
		return service.getAllUsers(token);
	}
	@PostMapping("/changepermission")
	public Response changeWebpagePermission(@RequestHeader String token,@RequestBody UpdateWebpagePermission updateWebpagePermission) {
		return service.changeWebPagePermission(token, updateWebpagePermission);
	}
}
