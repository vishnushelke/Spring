package com.bridgelabz.usermanagement.dto;

import lombok.Data;

@Data
public class UpdateUserDto {
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String country;
	private long extraPhone;
	private String address;
	private String password;
	private String profilePic;
}
