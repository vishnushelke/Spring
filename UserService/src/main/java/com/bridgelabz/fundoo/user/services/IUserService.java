package com.bridgelabz.fundoo.user.services;

import com.bridgelabz.fundoo.user.DTO.ForgetDTO;
import com.bridgelabz.fundoo.user.DTO.LoginDTO;
import com.bridgelabz.fundoo.user.DTO.RegisterDTO;
import com.bridgelabz.fundoo.user.DTO.setPasswordDTO;
import com.bridgelabz.fundoo.user.model.User;

public interface IUserService {
	
	public User registerUser(RegisterDTO registerDTO);
	
	public boolean loginUser(LoginDTO registerDTO);
	
	public User forgetPassword(ForgetDTO registerDTO);
	
	public User setPassword(setPasswordDTO registerDTO,String token);
	
	public boolean validateUser(String token);

}
