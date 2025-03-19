package com.redis.toy.api.dto;

import com.redis.toy.api.exception.CommonErrorCode;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UrlResDto {
	private CommonErrorCode errorCode;
	private String errorMessage;
	private String url;

	public UrlResDto(CommonErrorCode errorCode, String url) {
		this.errorCode = errorCode;
		this.url = url;
	}

}
