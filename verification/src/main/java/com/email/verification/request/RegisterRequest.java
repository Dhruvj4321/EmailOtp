package com.email.verification.request;

public class RegisterRequest {
	
	
	private String email;
	
	String name;

	public RegisterRequest(String email, String name) {
		super();
		this.email = email;
		this.name = name;
	}

	public RegisterRequest() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	

}