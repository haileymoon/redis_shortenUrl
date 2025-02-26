package com.redis.toy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.redis.toy.dto.Url;
import com.redis.toy.service.UrlMappingService;

@RestController
@RequestMapping("/bitly")
public class UrlMappingController {

	private final UrlMappingService urlMappingService;

	@Autowired
	public UrlMappingController(@Qualifier("UrlServiceImpl") UrlMappingService urlMappingService){
		this.urlMappingService = urlMappingService;
	}

	@GetMapping("")
	public ResponseEntity<Url> getOriginalUrl(@RequestParam String shortUrl){
		Url url = urlMappingService.getOriginalUrl(shortUrl);
		return ResponseEntity.status(HttpStatus.OK).body(url);
	}

	@PostMapping("")
	public ResponseEntity<Url> createShortUrl(@RequestBody Url requestUrl){
		Url responseUrl = urlMappingService.createShortUrl(requestUrl);
		if (responseUrl == null){
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseUrl);
	}
}
