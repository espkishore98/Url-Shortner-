package com.shortner.domain;

public class UserSpecificShort {
	private String longUrl;
	private String shortName;

	public String getLongUrl() {
		return longUrl;
	}

	public void setLongUrl(String longUrl) {
		this.longUrl = longUrl;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public UserSpecificShort(String longUrl, String shortName) {
		super();
		this.longUrl = longUrl;
		this.shortName = shortName;
	}

	public UserSpecificShort() {
		super();
	}

	@Override
	public String toString() {
		return "UserSpecificShort [longUrl=" + longUrl + ", shortName=" + shortName + "]";
	}

}
