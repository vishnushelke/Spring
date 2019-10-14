package com.bridgelabz.annotations;

import java.sql.*;
import java.util.Scanner;
import javax.annotation.*;
import org.springframework.stereotype.Component;

/**
 * PUROPSE : Creating connection with database
 * 
 * @author : Vishnu Shelke
 * @version: 1.0 Date : 14/10/2019
 *
 */
@Component
public class StudentDao {
	static Scanner sc = new Scanner(System.in);
	static Connection connection;

	private String url;

	private String username;

	private String password;

	private String driver;

	public static Scanner getSc() {
		return sc;
	}

	public static void setSc(Scanner sc) {
		StudentDao.sc = sc;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public static void setConnection(Connection connection) {
		StudentDao.connection = connection;
	}

	@PostConstruct
	public void init() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(url, username, password);
	}

	/**
	 * PUROPSE : Showing data from database
	 * 
	 * @author : Vishnu Shelke
	 * @version: 1.0 Date : 14/10/2019
	 *
	 */
	public void show() throws ClassNotFoundException, SQLException {

		String query = "select * from mess";
		Statement st = connection.createStatement();
		ResultSet rs = st.executeQuery(query);
		while (rs.next()) {
			System.out.println("name of student is " + rs.getString(1));
			System.out.println("Id of student is " + rs.getInt(2));
			System.out.println("food of student is " + rs.getString(3));
			System.out.println();
		}
	}

	/**
	 * PUROPSE : Deleting data from database
	 * 
	 * @author : Vishnu Shelke
	 * @version: 1.0 Date : 14/10/2019
	 *
	 */
	public void delete() throws ClassNotFoundException, SQLException {

		String query = "delete from mess where id=?";
		PreparedStatement st = connection.prepareStatement(query);
		System.out.println("enter id of student to be deleted");
		st.setInt(1, sc.nextInt());
		System.out.println(st.executeUpdate() + " row/s updated");
	}

	/**
	 * PUROPSE : Inserting data into database
	 * 
	 * @author : Vishnu Shelke
	 * @version: 1.0 Date : 14/10/2019
	 *
	 */
	public void insert() throws ClassNotFoundException, SQLException {

		String query = "insert into mess values(?,?,?)";
		PreparedStatement st = connection.prepareStatement(query);
		System.out.println("enter the name");
		st.setString(1, sc.next());
		System.out.println("enter id");
		st.setInt(2, sc.nextInt());
		System.out.println("Enter food");
		st.setString(3, sc.next());
		System.out.println(st.executeUpdate() + " row/s updated");
	}

	@PreDestroy
	public void destroy() throws SQLException {
		System.out.println("inside destroy");
		connection.close();
	}

}
