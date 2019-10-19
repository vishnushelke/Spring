/**
 * 
 */
package com.bridgelabz.crud.services;

import com.bridgelabz.crud.models.Laptop;
import com.bridgelabz.crud.models.Student;

public interface IHibernateCrud {

	public void createStudentLaptop(Laptop laptop, Student student);

	public void readStudent(int studentId);

	public void deleteStudent(int studentId);

	public void updateStudent(int studentId, Student student);

}
