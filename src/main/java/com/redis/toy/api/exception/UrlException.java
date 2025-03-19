package com.redis.toy.api.exception;

import com.google.gson.JsonObject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UrlException extends RuntimeException{
	private CommonErrorCode errorCode;
	private String errorMsg;

	public UrlException(CommonErrorCode errorCode, String errorMsg) {
		super(errorMsg); // 부모 클래스인 RuntimeException에 메시지를 전달
		this.errorCode = errorCode;
		this.errorMsg = errorMsg;
	}

	public String toString() {
		// gson을 dependency로 추가해줘야 함
		JsonObject jsonObject = new JsonObject();
		// 객체의 기본 문자열 표현이 출력됨 -> enum의 code
		jsonObject.addProperty("errorCode", this.errorCode.name());
		jsonObject.addProperty("errorMessage", this.errorMsg);
		return jsonObject.toString();
	}
}
