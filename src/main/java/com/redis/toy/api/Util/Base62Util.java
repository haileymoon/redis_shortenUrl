package com.redis.toy.api.Util;

import org.springframework.stereotype.Component;

@Component
public class Base62Util {
	private final char[] BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".toCharArray();
	private int base = BASE62.length;

	public String encode(int param){
		StringBuilder sb = new StringBuilder();
		while (param > 0){
			int i = param % base;
			sb.append(BASE62[i]);
			param /= base;
		}
		return sb.toString();
	}

	public int decode(String param){
		int result = 0;
		int power = 1;
		for(int i = 0; i < param.length(); i++){
			int digit = new String(BASE62).indexOf(param.charAt(i));
			result += digit * power;
			power *= base;
		}
		return result;
	}
}
