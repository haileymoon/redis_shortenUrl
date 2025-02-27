package com.redis.toy.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
	/*
		Redis stores data as strings, so objects need to be converted to a string format
		(serialized) when storing and back to objects
		(deserialized) when retrieving
		--> 저장하고자하는 클래스에 implements Serializable을 붙혀야 함
	*/
public class Url implements Serializable {
	private String urlName;
	private String originalUrl;
	private String shortUrl;
	private String regId;

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}
}


