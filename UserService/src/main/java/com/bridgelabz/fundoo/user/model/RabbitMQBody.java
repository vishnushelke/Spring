/******************************************************************************
*  Purpose: This is a RabbitMQ body.We can add respective data in this body and 
*  			send an email.
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   04-10-2019
*
******************************************************************************/

package com.bridgelabz.fundoo.user.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RabbitMQBody implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	private String subject;
	private String body;

}
