package com.bridgelabz.fundoo.note.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Response {

	private int statusCode;
	private String message;
	private Object data;

	@Override
	public String toString() {
		return "Response [statusCode=" + statusCode + ", message=" + message + ", data=" + data + "]";
	}

	public Response(int statusCode, String message, Object data) {
		super();
		this.statusCode = statusCode;
		this.message = message;
		this.data = data;
	}

}
