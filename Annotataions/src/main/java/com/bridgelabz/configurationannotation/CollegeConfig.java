package com.bridgelabz.configurationannotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CollegeConfig {

	@Bean(name = "principal")
	public Principal principalBean() {
		return new Principal();
	}
	
	@Bean(name="teacher")
	public Teacher teacherBean()
	{
		return new ImplTeacher();
	}

	@Bean(name = "college")
	public College getCollege() {
		College college = new College();
		college.setPrincipal(principalBean());
		college.setTeacher(teacherBean());
		return college;
	}

}
