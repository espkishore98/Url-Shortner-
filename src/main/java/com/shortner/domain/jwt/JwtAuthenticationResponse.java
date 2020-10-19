package com.shortner.domain.jwt;

public class JwtAuthenticationResponse {

	private String accessToken;
	private String tokenType;
	private String emailId;
	private String userName;
	private String externalId;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getTokenType() {
		return tokenType;
	}

	public void setTokenType(String tokenType) {
		this.tokenType = tokenType;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public JwtAuthenticationResponse(String accessToken, String tokenType, String emailId, String userName,
			String externalId) {
		super();
		this.accessToken = accessToken;
		this.tokenType = tokenType;
		this.emailId = emailId;
		this.userName = userName;
		this.externalId = externalId;
	}

	@Override
	public String toString() {
		return "JwtAuthenticationResponse [accessToken=" + accessToken + ", tokenType=" + tokenType + ", emailId="
				+ emailId + ", userName=" + userName + ", externalId=" + externalId + "]";
	}

}
