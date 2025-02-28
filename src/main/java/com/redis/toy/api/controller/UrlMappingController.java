package com.redis.toy.api.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.redis.toy.api.dto.Url;
import com.redis.toy.api.service.UrlMappingService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/bitly")
public class UrlMappingController {

	private final UrlMappingService urlMappingService;
	private final Logger LOG = LoggerFactory.getLogger(UrlMappingController.class);

	@Autowired
	public UrlMappingController(@Qualifier("UrlServiceImpl") UrlMappingService urlMappingService){
		this.urlMappingService = urlMappingService;
	}

	@GetMapping("")
	//ResponseEntity<Void> 에서 Void인 이유는 리다이렉트 response는 body가 필요하지 않기 때문
	public ResponseEntity<Void> getOriginalUrl(@RequestParam String shortUrl) throws URISyntaxException {
		LOG.info("Start: {}", LocalDateTime.now());
		Url url = urlMappingService.getOriginalUrl(shortUrl);
		LOG.info("End: {}", LocalDateTime.now());
		
		if (url == null || StringUtils.isEmpty(url.getOriginalUrl())){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		String resUrl = modifyUrlIfNeeded(url);

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(new URI(resUrl));
		return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).headers(httpHeaders).build();
	}

	@PostMapping("")
	public ResponseEntity<Url> createShortUrl(@RequestBody Url requestUrl){
		Url responseUrl = urlMappingService.createShortUrl(requestUrl);
		if (responseUrl == null){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseUrl);
	}

	private String modifyUrlIfNeeded(Url url) {
		if(url.getOriginalUrl().startsWith("https://") || url.getOriginalUrl().startsWith("http://")){
			return url.getOriginalUrl();
		}
		return "https://" + url.getOriginalUrl();
	}
}
