package com.bridgelabz.usermanagement.utility;

import org.springframework.stereotype.Component;

import com.bridgelabz.usermanagement.configuration.RabbitMQBody;
@Component
public class Utility {
	
	public RabbitMQBody getRabbitMqBody(String token,String email,String text) {
		RabbitMQBody body = new RabbitMQBody();
		body.setBody(text+token);
		body.setEmail(email);
		return body;
	}

}
