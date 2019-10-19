package com.bridgelabz.crud.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.bridgelabz.crud.models.Laptop;
import com.bridgelabz.crud.models.Student;

public class ImplHibernateCrud implements IHibernateCrud {

	static Configuration config = new Configuration().configure("hibernet.xml").addAnnotatedClass(Student.class)
			.addAnnotatedClass(Laptop.class);

	static ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(config.getProperties())
			.buildServiceRegistry();
	static SessionFactory sf = config.buildSessionFactory(reg);
	static Session session = sf.openSession();
	static Transaction tx = session.beginTransaction();

	@Override
	public void createStudentLaptop(Laptop laptop, Student student) {
		session.save(student);
		session.save(laptop);
		tx.commit();

	}

	@Override
	public void readStudent(int studentId) {

		Student student = (Student) session.get(Student.class, studentId);
		System.out.println(student);
	}

	@Override
	public void deleteStudent(int studentId) {
		System.out.println("Student with id " + studentId + " deleting....");
		session.delete(session.get(Student.class, studentId));
		System.out.println("deleted!");
		tx.commit();

	}

	@Override
	public void updateStudent(int studentId, Student student) {
		System.out.println("Student with id " + studentId + " updating....");
		session.update(session.get(Student.class, studentId));
		System.out.println("Updated!");
		tx.commit();
	}

}
