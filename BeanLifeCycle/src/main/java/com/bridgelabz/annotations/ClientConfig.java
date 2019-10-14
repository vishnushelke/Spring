package com.bridgelabz.annotations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:connection.properties")
public class ClientConfig {
	@Bean
	public StudentDao getStudentDao() {
		return new StudentDao();
	}
}
