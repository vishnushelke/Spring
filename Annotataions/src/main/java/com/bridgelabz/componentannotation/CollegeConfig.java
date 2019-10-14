package com.bridgelabz.componentannotation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "com.bridgelabz.componentannotation")
@PropertySource("classpath:collegeInfo.properties")
public class CollegeConfig {

//	@Bean(name = "principal")
//	public Principal principalBean() {
//		return new Principal();
//	}
//	
//	@Bean(name="teacher")
//	public Teacher teacherBean()
//	{
//		return new ImplTeacher();
//	}
//
//	@Bean(name = "college")
//	public College getCollege() {
//		College college = new College();
//		college.setPrincipal(principalBean());
//		college.setTeacher(teacherBean());
//		return college;
//	}

}
