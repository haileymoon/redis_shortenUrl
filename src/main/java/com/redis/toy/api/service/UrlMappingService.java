package com.redis.toy.api.service;

import com.redis.toy.api.dto.Url;


public interface UrlMappingService {
	Url getOriginalUrl(String shortUrl);
	Url createShortUrl(Url requestUrl);
}
