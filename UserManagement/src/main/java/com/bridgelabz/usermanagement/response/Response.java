package com.bridgelabz.usermanagement.response;

import lombok.Data;

@Data
public class Response {

	private int statusCode;
	private String message;
	private Object data;

	public Response(int statusCode, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

}
