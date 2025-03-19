package com.redis.toy.api.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.redis.toy.api.dto.Url;
import com.redis.toy.api.dto.UrlResDto;
import com.redis.toy.api.exception.CommonErrorCode;
import com.redis.toy.api.exception.UrlException;
import com.redis.toy.api.service.UrlMappingService;

import io.micrometer.common.util.StringUtils;

@RestController
@RequestMapping("/short-url")
public class UrlMappingController {

	private final UrlMappingService urlMappingService;
	private final Logger LOG = LoggerFactory.getLogger(UrlMappingController.class);

	@Autowired
	public UrlMappingController(@Qualifier("UrlServiceImpl") UrlMappingService urlMappingService){
		this.urlMappingService = urlMappingService;
	}

	@GetMapping("")
	public ResponseEntity<Void> getOriginalUrl(@RequestParam String shortUrl) throws URISyntaxException {
		String url = urlMappingService.getOriginalUrl(shortUrl);
		
		if (StringUtils.isEmpty(url)){
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(new URI(url));
		return ResponseEntity.status(HttpStatus.TEMPORARY_REDIRECT).headers(httpHeaders).build();
	}

	@PostMapping("")
	public UrlResDto createShortUrl(@RequestBody Url requestUrl){
		String responseUrl = urlMappingService.createShortUrl(requestUrl);
		if (responseUrl == null){
			throw new UrlException(CommonErrorCode.NO_DATA, "created short url is empty");
		}
		return new UrlResDto(CommonErrorCode.OK, responseUrl);
	}

}
