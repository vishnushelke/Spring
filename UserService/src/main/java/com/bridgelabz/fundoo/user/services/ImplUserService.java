package com.bridgelabz.fundoo.user.services;

import java.util.Date;

import com.bridgelabz.fundoo.user.exception.custom.ForgetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.LoginException;

import com.bridgelabz.fundoo.user.exception.custom.RegistrationException;
import com.bridgelabz.fundoo.user.exception.custom.SetPasswordException;
import com.bridgelabz.fundoo.user.exception.custom.ValidationException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.bridgelabz.fundoo.user.DTO.ForgetDTO;
import com.bridgelabz.fundoo.user.DTO.LoginDTO;
import com.bridgelabz.fundoo.user.DTO.RegisterDTO;
import com.bridgelabz.fundoo.user.DTO.setPasswordDTO;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.repository.UserConfig;
import com.bridgelabz.fundoo.user.repository.UserRepository;
import com.bridgelabz.fundoo.user.utility.UserUtility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class ImplUserService implements IUserService {

	@Autowired
	UserRepository repository;

	@Autowired
	MailSender mailSender;

	@Autowired
	UserConfig config;

	@Autowired
	UserUtility utility;

	@Autowired
	ModelMapper mapper;

	@Override
	public User registerUser(RegisterDTO registerDTO) {
		registerDTO.setPassword(config.passEndcode().encode(registerDTO.getPassword()));
		if (!alreadyAvailable(registerDTO.getEmail())) {
			User user = mapper.map(registerDTO, User.class);
			String token = Jwts.builder().setSubject(registerDTO.getEmail()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "registerUser").compact();
			SimpleMailMessage message = utility.getMessage(token);
			message.setSubject("response to your registration");
			message.setTo("shelkeva@gmail.com");
			message.setText("validate your email" + "\n" + "http://localhost:8080/user/validate/" + token);
			mailSender.send(message);
			return repository.save(user);

		} else {
			throw new RegistrationException("email not yet registered");
		}

	}

	@Override
	public boolean loginUser(LoginDTO loginDTO) {
		// TODO Auto-generated method stub
		System.out.println(loginDTO.getEmail()+" "+loginDTO.getPassword().length());
		if(!(loginDTO.getEmail().equals(null) || loginDTO.getPassword().length()<6))
		{
			return repository.findAll().stream().anyMatch(t -> t.getEmail().equals(loginDTO.getEmail())
					&& config.passEndcode().matches(loginDTO.getPassword(), t.getPassword()));
		}
		else
		{
			throw new LoginException("invlid email id or password");
		}
		
	}

	@Override
	public User forgetPassword(ForgetDTO forgetDTO) {
		// TODO Auto-generated method stub
		if (alreadyAvailable(forgetDTO.getEmail())) {

			String token = Jwts.builder().setSubject(forgetDTO.getEmail()).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretKey").compact();
			SimpleMailMessage message = utility.getMessage(token);
			mailSender.send(message);
		}
		else
		{
			throw new ForgetPasswordException("Email id you entered is not registered");
		}
		return null;
	}

	@Override
	public User setPassword(setPasswordDTO setPasswordDTO, String token) {
		// TODO Auto-generated method stub
		Claims claim = Jwts.parser().setSigningKey("secretKey").parseClaimsJws(token).getBody();
		String email = claim.getSubject();
		if(alreadyAvailable(email))
		{
			User user = alreadyAvailableUser(email);
			user.setPassword(config.passEndcode().encode(setPasswordDTO.getPassword()));
			return repository.save(user);	
		}
		else
		{
			throw new SetPasswordException("This is not valid link");
		}
		
	}

	public boolean alreadyAvailable(String email) {
		System.out.println(email);
		return repository.findAll().stream().anyMatch(t -> t.getEmail().equals(email));
	}

	public User alreadyAvailableUser(String email) {
		System.out.println(email);
		return repository.findAll().stream().findAny().filter(t -> t.getEmail().equals(email)).get();
	}

	@Override
	public boolean validateUser(String token) {
		// TODO Auto-generated method stub
		Claims claim = Jwts.parser().setSigningKey("registerUser").parseClaimsJws(token).getBody();
		String email = claim.getSubject();
		System.out.println(email);
		User user = repository.findAll().stream().filter(i -> i.getEmail().equals(email)).findAny().orElse(null);
		user.setIsactive(true);
		repository.save(user);
		return true;
	}

}
