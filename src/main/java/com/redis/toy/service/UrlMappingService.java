package com.redis.toy.service;

import com.redis.toy.dto.Url;


public interface UrlMappingService {
	Url getOriginalUrl(String shortUrl);
	Url createShortUrl(Url requestUrl);
}
