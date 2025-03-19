package com.redis.toy.api.dto;

import java.io.Serial;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

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
	/*
		💡 왜 serialVersionUID를 넣어야 할까?
		- 직렬화 버전 관리: 직렬화된 객체를 역직렬화할 때, UID가 없고 클래스의 구조가 바뀌면 **InvalidClassException**이 발생할 수 있음
		- 호환성 보장: 만약 클래스 구조를 변경해도 동일한 serialVersionUID를 가지고 있으면 기존 직렬화된 데이터를 정상적으로 역직렬화할 수 있음
	*/

	@Serial
	private static final long serialVersionUID = 1L;

	private String urlName;
	private String originalUrl;
	private String shortUrl;
	private String regId;

	public String getUrlName() {
		return urlName;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}


	public String getRegId() {
		return regId;
	}

}


