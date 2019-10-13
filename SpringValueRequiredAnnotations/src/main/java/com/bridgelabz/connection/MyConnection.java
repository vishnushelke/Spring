package com.bridgelabz.connection;

import org.springframework.beans.factory.annotation.Value;

public class MyConnection {
	@Value("${connection.url}")
	private String url;
	@Value("${connection.username}")
	private String username;
	@Value("${connection.password}")
	private String password;
	@Value("${connection.driver}")
	private String driver;

	public String getUrl() {
		return url;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getDriver() {
		return driver;
	}
//
//	public void displayConnection() {
//		System.out.println(url + " " + username + "\n" + password + " " + driver);
//	}
}
