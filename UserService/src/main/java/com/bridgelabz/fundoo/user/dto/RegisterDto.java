/******************************************************************************
*  Purpose: This class is a DTO class which maps the values from response which 
*  			is caught by @requestbody
*
*  @author  Vishnu Shelke
*  @version 1.0
*  @since   23-10-2019
*
******************************************************************************/
package com.bridgelabz.fundoo.user.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class RegisterDto implements Serializable{

	private static final long serialVersionUID = 1L;
	@NotEmpty(message = "first name can not be empty")
//	@Pattern(regexp = "^[A-Z a-z]+$",message = "kahipan")
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private long phonenumber;

	public String getFirstname() {
		return firstname;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
