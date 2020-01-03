package com.bridgelabz.usermanagement.service;

import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.usermanagement.dto.CreateUserDto;
import com.bridgelabz.usermanagement.dto.LoginDto;
import com.bridgelabz.usermanagement.dto.UpdateUserDto;
import com.bridgelabz.usermanagement.dto.UpdateWebpagePermission;
import com.bridgelabz.usermanagement.response.Response;

public interface IUserService {
	
	public Response registerAdmin(CreateUserDto createUserDto);
	
	public Response login(LoginDto loginDto);
	
	public Response createUser(CreateUserDto createUserDto,String token);
	
	public Response validateUser(String token);
	
	public Response forgotPassword(String email);
	
	public Response updateUser(int userId,UpdateUserDto updateUserDto,String token);
	
	public Response deleteUser(int userId,String token);
	
	public Response updateProfilePic(int userId,String token,MultipartFile file);
	
	public Response clearProfilePic(int userId,String token,MultipartFile file);
	
	public Response getLoginHistory(int userId,String token);
	
	public Response getAlltimeData(String token);
	
	public Response getThisYearData(String token);
	
	public Response getThisMonthData(String token);

	public Response getAllUsers(String token);
	
	public Response getActiveUsers(String token);
	
	public Response getInactiveUsers(String token);
	
	public Response changeWebPagePermission(String token,UpdateWebpagePermission permission);
}
