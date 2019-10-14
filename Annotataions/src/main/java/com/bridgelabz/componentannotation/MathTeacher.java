package com.bridgelabz.componentannotation;

import org.springframework.stereotype.Component;

@Component
public class MathTeacher implements Teacher{

	@Override
	public void teach() {
		System.out.println("I am a math Teacher");
		
	}

}
