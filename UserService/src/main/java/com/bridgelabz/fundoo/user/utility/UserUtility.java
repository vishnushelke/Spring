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

@Component
public class UserUtility {
	
	/**
	 * Purpose This method will create an object of SimpleMailMessage and returns
	 * 
	 * @return SimpleMailMessage
	 */
	public SimpleMailMessage getMessage()
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("shelkeva@gmail.com");
		return message;
	}

}
