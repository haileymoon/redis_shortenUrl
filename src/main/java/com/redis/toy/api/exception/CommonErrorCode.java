package com.redis.toy.api.exception;

import lombok.Getter;

@Getter
public enum CommonErrorCode {
	OK(200, "SUCESS", 200),
	DUPLICATE_DATA(20001, "DUPLICATE URL DATA", 400),
	NO_DATA(99994, "NO DATA", 500),
	;
	private final int errorCode;
	private final String errorMessage;
	private final int httpStatus;

	// 생성자를 통해 인스턴스화 (각 열거 상수에 고유한 값을 할당) 가능
	CommonErrorCode(int errorCode, String errorMessage, int httpStatus) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.httpStatus = httpStatus;
	}
}
