package com.bridgelabz.crud.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Student {
	@Id
	private int id;
	private String name;
	private String lastname;
	@OneToOne(mappedBy = "student")
	private Laptop laptop;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Laptop getLaptop() {
		return laptop;
	}

	public void setLaptop(Laptop laptop) {
		this.laptop = laptop;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", lastname=" + lastname + "]";
	}

}
