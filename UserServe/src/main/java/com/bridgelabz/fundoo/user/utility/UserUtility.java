package com.bridgelabz.fundoo.user.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
public class UserUtility {
	
	public SimpleMailMessage getMessage(String token)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("shelkeva@gmail.com");
		message.setSubject("response to your forget password");
		message.setText("http://localhost:8080/user/setpassword/"+token);
		return message;
	}

}
