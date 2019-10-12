package com.bridgelabz.dependencyinjection;

public class Student {
	private int id;
	private String name;

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void displayInfo() {
		System.out.println("Student name is " + name + " and id is " + id);
	}
}
