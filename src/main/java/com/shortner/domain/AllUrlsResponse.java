package com.shortner.domain;

public class AllUrlsResponse {
	private Long userId;
	private Long urlId;
	private String LongUrl;
	private String ShortUrl;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getUrlId() {
		return urlId;
	}
	public void setUrlId(Long urlId) {
		this.urlId = urlId;
	}
	public String getLongUrl() {
		return LongUrl;
	}
	public void setLongUrl(String longUrl) {
		LongUrl = longUrl;
	}
	public String getShortUrl() {
		return ShortUrl;
	}
	public void setShortUrl(String shortUrl) {
		ShortUrl = shortUrl;
	}
	@Override
	public String toString() {
		return "AllUrlsResponse [userId=" + userId + ", urlId=" + urlId + ", LongUrl=" + LongUrl + ", ShortUrl="
				+ ShortUrl + "]";
	}
	
}
