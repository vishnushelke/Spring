package com.bridgelabz.diusingconstructor;

public class StudentConstructor {
	private int id;
	private String name;

	public StudentConstructor(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public void displayInfo() {
		System.out.println("Student name is " + name + " and id is " + id);
	}
}
