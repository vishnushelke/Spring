package com.bridgelabz.autowiringusingannotations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Body {
	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beansannotation.xml");
		Human human =context.getBean("human",Human.class);
		human.startPumping();
	}
}
