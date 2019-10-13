package com.bridgelabz.loadingfrompropertiesfile;

import org.springframework.beans.factory.annotation.Value;

public class Student {
	@Value("${student.name}")
	private String name;
	@Value("${student.interestedCourse}")
	private String interestCourse;
	@Value("${student.hobby}")
	private String hobby;

	public void studentInfo()
	{
		System.out.println("student name is "+name+"\ninterest course is "+interestCourse+"\nhobby is "+hobby);
	}

}
