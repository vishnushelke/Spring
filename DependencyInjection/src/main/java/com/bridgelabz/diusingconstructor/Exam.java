package com.bridgelabz.diusingconstructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Exam {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("beansconstructor.xml");
		StudentConstructor rishi = context.getBean("rishi",StudentConstructor.class);
		rishi.displayInfo();
		StudentConstructor vishnu = context.getBean("vishnu",StudentConstructor.class);
		vishnu.displayInfo();
	}

}
