package com.bridgelabz.fundoo.user.service;

import com.bridgelabz.fundoo.user.dto.ForgetDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.model.User;

public interface IUserService {
	
	public boolean loginUser(LoginDTO loginDTO);
	
	public User registerUser(RegisterDTO registerDTO);
	
	public User forgetPassword(ForgetDTO forgetDTO);
	
	public void setPassword(SetPasswordDTO setPasswordDTO);
	
	public boolean alreadyRegistered(String email);

}
