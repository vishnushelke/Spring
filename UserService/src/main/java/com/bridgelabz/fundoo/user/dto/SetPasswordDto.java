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

public class SetPasswordDto {

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
