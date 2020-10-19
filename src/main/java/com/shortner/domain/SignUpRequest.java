package com.shortner.domain;

public class SignUpRequest {
	private String username;
	private String emailId;
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public SignUpRequest(String username, String emailId, String password) {
		super();
		this.username = username;
		this.emailId = emailId;
		this.password = password;
	}

	public SignUpRequest() {
		super();
	}

	@Override
	public String toString() {
		return "SignUpRequest [username=" + username + ", password=" + password + "]";
	}

}
