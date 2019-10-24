package com.bridgelabz.fundoo.note.noteconfig;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NoteConfig {

	@Bean
	public ModelMapper getMapper()
	{
		return new ModelMapper();
	}
	

}
