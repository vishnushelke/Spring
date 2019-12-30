package com.bridgelabz.usermanagement.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginDto {
	@NotBlank(message = "email Id cannot be null")
	private String userName;
	@NotBlank(message = "password field cannot be null")
	private String password;
}
