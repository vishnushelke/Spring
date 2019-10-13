package com.bridgelabz.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DbConnection {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		@SuppressWarnings("resource")
		ApplicationContext context = new ClassPathXmlApplicationContext("beansconnection.xml");
		MyConnection con = context.getBean("connection", MyConnection.class);
		Class.forName(con.getDriver());
		Connection connection = DriverManager.getConnection(con.getUrl(), con.getUsername(), con.getPassword());
		String query = "select * from customer";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
		}
	}

}
