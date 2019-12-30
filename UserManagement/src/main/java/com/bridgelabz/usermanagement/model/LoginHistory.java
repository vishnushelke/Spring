package com.bridgelabz.usermanagement.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class LoginHistory {

	@Id
	private int userId;
	private String loginTime;
}
