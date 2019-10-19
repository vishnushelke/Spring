package com.bridgelabz.fundoo.user.service;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.dto.ForgetDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.RegisterDTO;
import com.bridgelabz.fundoo.user.dto.SetPasswordDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.IUserRepository;
import com.bridgelabz.fundoo.user.repository.UserConfig;
import com.bridgelabz.fundoo.user.utility.UserServiceUtlity;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImplUserService implements IUserService {

	@Autowired
	private UserServiceUtlity utility;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserConfig userconfig;

	@Autowired
	private IUserRepository userRepository;
	@Autowired
	ModelMapper modelMapper;

	@Override
	public boolean loginUser(LoginDTO loginDTO) {
		return userRepository.findAll().stream().anyMatch(t -> t.getEmail().equals(loginDTO.getEmailId())
				&& userconfig.passEndcode().matches(loginDTO.getPassword(), t.getPassword()));

	}

	@Override
	public User registerUser(RegisterDTO registerDTO) {
		registerDTO.setPassword(userconfig.passEndcode().encode(registerDTO.getPassword()));
		System.out.println(registerDTO.getEmail().getClass().getName());
		if (!alreadyRegistered(registerDTO.getEmail())) {
			User user = modelMapper.map(registerDTO, User.class);
			return userRepository.save(user);
		} else {

		}

		return null;
	}

	@Override
	public User forgetPassword(ForgetDTO forgetDTO) {
		if (alreadyRegistered(forgetDTO.getEmail())) {
			String token = Jwts.builder().setSubject("email").setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			SimpleMailMessage message = utility.sendMail(token);
			mailSender.send(message);
		}
		return null;
	}

	@Override
	public void setPassword(SetPasswordDTO setPasswordDTO) {
		// TODO Auto-generated method stub
		
		if (alreadyRegistered(setPasswordDTO.getEmail())) {
			User user = modelMapper.map(setPasswordDTO, User.class);
			userRepository.save(user);
		}
	}

	@Override
	public boolean alreadyRegistered(String email) {
		System.out.println(email);

		return userRepository.findAll().stream().anyMatch(t -> t.getEmail().equals(email));
	}

}
