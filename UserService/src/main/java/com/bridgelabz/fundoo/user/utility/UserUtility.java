/******************************************************************************
*  Purpose: This is a utility containing all methods which called in program
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/

package com.bridgelabz.fundoo.user.utility;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.user.model.RabbitMQBody;
import com.bridgelabz.fundoo.user.services.StaticReference;

@Component
public class UserUtility {
	
	/**
	 * Purpose: This method will create an object of SimpleMailMessage and returns
	 * 
	 *  @param token
	 *  
	 * @return SimpleMailMessage
	 */
	public SimpleMailMessage getMessage(String token)
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("shelkeva@gmail.com");
		message.setSubject(StaticReference.REGISTRATION_RESPONSE);
		message.setText(StaticReference.REGISTRATION_MAIL_TEXT + token);
		return message;
	}

	/**
	 *  Purpose: This method will create an object of RabbitMQBody and returns
	 * @param token
	 * @param email
	 * @return RabbitMQBody object
	 */
	public RabbitMQBody getRabbitBody(String token,String email)
	{
		RabbitMQBody body = new RabbitMQBody();
		body.setEmail(email);
		body.setBody(StaticReference.REGISTRATION_MAIL_TEXT + token);
		body.setSubject(StaticReference.REGISTRATION_RESPONSE);
		return body;
		
	}
}
