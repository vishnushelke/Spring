package com.bridgelabz.configclass;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Client {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context =new ClassPathXmlApplicationContext("beans.xml");
		College college = context.getBean("college",College.class);
		college.show();
	}

}
