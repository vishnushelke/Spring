package com.bridgelabz.dependencyinjection;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
		Student rishi = context.getBean("rishi",Student.class);
		rishi.displayInfo();
		Student vishnu = context.getBean("vishnu",Student.class);
		vishnu.displayInfo();
	}

}
