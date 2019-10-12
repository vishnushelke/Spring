package com.bridgelabz.autowiringusingannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class Human {

	@Autowired
	@Qualifier("octopusHeart")
	private Heart heart;

//	public Human() {}
//	
//	
//	public Human(Heart heart) {
//		this.heart = heart;
//	}
//
//
//	public void setHeart(Heart heart) {
//		this.heart = heart;
//	}

	public void startPumping() {
		heart.pump();
		System.out.println("name of animal is "+heart.getNameOfAnimal());
		System.out.println("number of hearts of animal are "+heart.getNumberOfHeart());
	}

}
