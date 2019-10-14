package com.bridgelabz.beanannotation;

import org.springframework.context.annotation.Bean;

public class CollegeConfig {
	
	@Bean(name = "college")//this is the bean
	public College getCollege()
	{
		College college = new College();
		return college;
	}

}
