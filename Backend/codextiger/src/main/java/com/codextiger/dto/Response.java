package com.codextiger.dto;

public class Response {
	private String message;
	private LoginResponse user;
	
	
	public Response(String message, LoginResponse user) {
		this.message = message;
		this.user = user;
	}
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public LoginResponse getUser() {
		return user;
	}
	public void setUser(LoginResponse user) {
		this.user = user;
	}
	
	

}
