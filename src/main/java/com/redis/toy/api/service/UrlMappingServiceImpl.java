package com.redis.toy.api.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.redis.toy.api.Util.Base62Util;
import com.redis.toy.api.dao.UrlMappingRepository;
import com.redis.toy.api.dto.Url;
import com.redis.toy.api.model.UrlModel;

@Service("UrlServiceImpl")
public class UrlMappingServiceImpl implements UrlMappingService {

	private UrlMappingRepository urlMappingRepository;

	private Base62Util base62Util;

	@Autowired
	public UrlMappingServiceImpl(UrlMappingRepository urlMappingRepository, Base62Util base62Util){
		this.urlMappingRepository = urlMappingRepository;
		this.base62Util = base62Util;
	}

	@Override
	@Cacheable(value = "originalUrl", key = "#shortUrl")
	public Url getOriginalUrl(String shortUrl){
		// base 64 변환
		int seq = base62Util.decode(shortUrl);
		// DB 조회
		UrlModel urlModel = selectOriginalUrl(seq);
		if (urlModel == null){
			return new Url();
		}
		return setUrl(urlModel);
	}

	@Override
	public Url createShortUrl(Url requestUrl){

		UrlModel urlModel = makeUrlModel(requestUrl);

		// get seq num
		int seq = saveUrl(urlModel).getSeq();
		// convert original url to short url
		String shortUrl = base62Util.encode(seq);
		// update short url - https://brunch.co.kr/@anonymdevoo/37
		urlModel.setShortUrl(shortUrl);
		urlModel = urlMappingRepository.save(urlModel);
		return setUrl(urlModel);
	}

	private static UrlModel makeUrlModel(Url requestUrl) {
		UrlModel urlModel = new UrlModel();
		urlModel.setUrlName(requestUrl.getUrlName());
		urlModel.setOriginalUrl(requestUrl.getOriginalUrl());
		urlModel.setRegId(requestUrl.getRegId());
		urlModel.setRegDate(LocalDateTime.now());
		return urlModel;
	}

	public Url setUrl(UrlModel urlModel){
		Url url = new Url();
		url.setRegId(urlModel.getRegId());
		url.setOriginalUrl(urlModel.getOriginalUrl());
		url.setShortUrl(urlModel.getShortUrl());
		url.setUrlName(urlModel.getUrlName());
		return url;
	}
	public UrlModel saveUrl(UrlModel urlModel){
		return urlMappingRepository.save(urlModel);
	}

	public UrlModel selectOriginalUrl(int seq){
		return urlMappingRepository.findBySeq(seq);
	}
}
