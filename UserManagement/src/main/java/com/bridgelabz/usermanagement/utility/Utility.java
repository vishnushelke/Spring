package com.bridgelabz.usermanagement.utility;

import org.springframework.stereotype.Component;

import com.bridgelabz.usermanagement.configuration.RabbitMQBody;
@Component
public class Utility {
	
	public RabbitMQBody getRabbitMqBody(String token,String email) {
		RabbitMQBody body = new RabbitMQBody();
		body.setBody("click to verify\nhttp://localhost:8080/verify/"+token);
		body.setEmail(email);
		return body;
	}

}
