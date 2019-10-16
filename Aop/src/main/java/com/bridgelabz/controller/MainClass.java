package com.bridgelabz.controller;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.bridgelabz.services.Shape;

public class MainClass {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
		Shape shape=context.getBean("shape",Shape.class);
		shape.getCircle().setName("badawalaCircle");
//		System.out.println(shape.getCircle().getName());
//		System.out.println(shape.getTriangle().getName());
		((ClassPathXmlApplicationContext)context).close();
	}

}
