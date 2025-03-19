package com.redis.toy.api.service;

import com.redis.toy.api.dto.Url;


public interface UrlMappingService {
	String getOriginalUrl(String shortUrl);
	String createShortUrl(Url requestUrl);
}
