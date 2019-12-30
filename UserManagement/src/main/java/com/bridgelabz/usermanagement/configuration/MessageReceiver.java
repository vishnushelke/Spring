package com.bridgelabz.usermanagement.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MessageReceiver {
	
	@Autowired
	private JavaMailSender mailSender;

	public void sendMessage(RabbitMQBody body) {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(body.getEmail());
		message.setText(body.getBody());
		message.setSubject(body.getSubject());
		mailSender.send(message);
	}
}
