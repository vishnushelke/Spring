package com.bridgelabz.fundoo.user.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class UserServiceUtlity {
	
	
	public SimpleMailMessage sendMail(String token)
	{
		SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
		
		simpleMailMessage.setTo("shelkeva@gmail.com");
		simpleMailMessage.setSubject("response to your forget password");
		simpleMailMessage.setText("http://localhost:8080/user/setpassword/"+token);
		
		return simpleMailMessage;
	}

}
