package com.bridgelabz.usermanagement.dto;

import lombok.Data;

@Data
public class CreateUserDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String emailId;
	private String dateOfBirth;
	private String gender;
	private String country;
	private long phoneNumber;
	private long extraPhone;
	private String address;
	private String userName;
	private String password;
	private String userRole;
	private boolean status;
	private String profilePic;
}
