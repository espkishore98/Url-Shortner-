package com.shortner.domain;

public class LoginRequest {
	private String emailId;
	private String password;

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginRequest(String emailId, String password) {
		super();
		this.emailId = emailId;
		this.password = password;
	}

	public LoginRequest() {
		super();
	}

	@Override
	public String toString() {
		return "LoginRequest [emailId=" + emailId + ", password=" + password + "]";
	}

}
