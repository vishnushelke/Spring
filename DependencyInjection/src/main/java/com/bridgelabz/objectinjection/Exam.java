package com.bridgelabz.objectinjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam {

	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("beansobject.xml");
		Student student = context.getBean("stu",Student.class);
		student.cheating();
		
		AnotherStudent vishnu = context.getBean("anostu",AnotherStudent.class);
		vishnu.cheating();
	}

}
