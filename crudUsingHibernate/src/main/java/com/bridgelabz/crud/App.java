package com.bridgelabz.crud;

import java.util.Scanner;

import com.bridgelabz.crud.models.Laptop;
import com.bridgelabz.crud.models.Student;
import com.bridgelabz.crud.services.ImplHibernateCrud;

public class App {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		ImplHibernateCrud util = new ImplHibernateCrud();
		boolean exit = false;
		while(!exit)
		{
			System.out.println("what do you want to do?\n1.create\n2.read\n3.update\n4.delete\n5.exit");
			switch (sc.nextInt()) {
			case 1:// creating student
				System.out.println("Enter the id");
				int id = sc.nextInt();
				System.out.println("Enter name");
				String name = sc.next();
				System.out.println("Enter Last name");
				String lastName = sc.next();
				Student student = createStudent(id, name, lastName);
				System.out.println("Enter laptop id");
				int lId = sc.nextInt();
				System.out.println("Enter laptop name");
				String lName = sc.next();
				Laptop laptop = createLaptop(lId, lName);
				laptop.setStudent(student);
				student.setLaptop(laptop);
				util.createStudentLaptop(laptop, student);
				break;

			case 2:// reading student with id
				System.out.println("Enter Student id");
				int idRead = sc.nextInt();
				util.readStudent(idRead);
				break;

			case 3: // updating student with id
				System.out.println("Enter Student id");
				int idUpdate = sc.nextInt();
				System.out.println("enter new name");
				String nameUpdate = sc.next();
				System.out.println("enter new lastName");
				String lNameUpdate = sc.next();
				Student updateStudent = createStudent(idUpdate, nameUpdate, lNameUpdate);
				util.updateStudent(idUpdate, updateStudent);
				break;

			case 4:// deleting a student with id
				System.out.println("Enter Student id");
				int idDelete = sc.nextInt();
				util.deleteStudent(idDelete);
				break;

			case 5://exiting
				exit=true;
				System.out.println("Exiting...");
				break;
				
			default:
				System.out.println("Enter valid input");
				break;
			}

		}
				sc.close();
	}

	public static Student createStudent(int id, String name, String lastName) {
		Student student = new Student();
		student.setId(id);
		student.setLastname(lastName);
		student.setName(name);
		return student;
	}

	public static Laptop createLaptop(int id, String name) {
		Laptop laptop = new Laptop();
		laptop.setlId(id);
		laptop.setlName(name);
		return laptop;
	}

}
