/******************************************************************************
*  Purpose: This is a configuration class of note.
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   04-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.note.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class NoteConfig {

	/**
	 * purpose: this method will create an object of modelmapper and return it.
	 * 			
	 * @return object of model mapper
	 */
	@Bean
	@Primary
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
	

}
