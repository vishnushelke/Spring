package com.bridgelabz.hibernet;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistryBuilder;
import org.hibernate.Transaction;

import org.hibernate.service.ServiceRegistry;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class App {

	public static void main(String[] args) {
		
		//for writing
//		Alien telusko= new Alien();
//		telusko.setAlienId(101);
//		telusko.setColour("black");
//		AlienName name = new AlienName();
//		name.setfName("vishnu");
//		name.setlName("shelke");
//		name.setmName("annarao");
//		telusko.setAlienName(name);
		
		//for reading
		Alien telusko;

		Configuration con = new Configuration().configure("NewFile.xml").addAnnotatedClass(Alien.class);
		
		ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(con.getProperties()).buildServiceRegistry();
		SessionFactory sf = con.buildSessionFactory(reg);
		Session session=sf.openSession();
		//getting data from database
		telusko=(Alien)session.get(Alien.class, 101);
		Transaction tx = session.beginTransaction();
//		session.save(telusko);
		tx.commit();
		System.out.println(telusko);
	}

}
