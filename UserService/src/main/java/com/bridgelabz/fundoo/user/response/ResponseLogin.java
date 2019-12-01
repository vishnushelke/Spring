package com.bridgelabz.fundoo.user.response;

import com.bridgelabz.fundoo.user.model.User;

import lombok.Data;
@Data
public class ResponseLogin {
	public ResponseLogin(int statusCode, String message, Object data, User user) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
		this.user = user;
	}
	private int statusCode;
	private String message;
	private Object data;
	private User user;

}
