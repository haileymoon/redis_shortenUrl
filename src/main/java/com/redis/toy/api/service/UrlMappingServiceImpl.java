package com.redis.toy.api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.redis.toy.api.Util.Base62Util;
import com.redis.toy.api.dao.UrlMappingRepository;
import com.redis.toy.api.dto.Url;
import com.redis.toy.api.exception.CommonErrorCode;
import com.redis.toy.api.exception.UrlException;
import com.redis.toy.api.model.UrlModel;

import io.micrometer.common.util.StringUtils;

@Service("UrlServiceImpl")
public class UrlMappingServiceImpl implements UrlMappingService {

	private UrlMappingRepository urlMappingRepository;

	private Base62Util base62Util;

	private RedisTemplate<String, String> redisTemplate;
	@Autowired
	public UrlMappingServiceImpl(UrlMappingRepository urlMappingRepository, Base62Util base62Util, RedisTemplate<String, String> redisTemplate){
		this.urlMappingRepository = urlMappingRepository;
		this.base62Util = base62Util;
		this.redisTemplate = redisTemplate;
	}

	@Override
	public String getOriginalUrl(String shortUrl){
		// 캐시에서 조회한 값이 Object 타입
		String originalUrl = (String)redisTemplate.opsForValue().get(shortUrl);
		if (!StringUtils.isEmpty(originalUrl)){
			return originalUrl;
		}
		List<UrlModel> urlModels = urlMappingRepository.findByShortUrl(shortUrl);
		if (urlModels.isEmpty()){
			throw new UrlException(CommonErrorCode.NO_DATA, "Empty Url");
		} else if (urlModels.size() > 1){
			throw new UrlException(CommonErrorCode.DUPLICATE_DATA, "Multiple Short Url Exists");
		}
		return urlModels.get(0).getOriginalUrl();
	}

	@Override
	@CachePut(value = "urlCache", key = "#shortUrl")
	public String createShortUrl(Url requestUrl){

		UrlModel urlModel = makeUrlModel(requestUrl);

		int seq = saveUrl(urlModel).getSeq();
		String shortUrl = base62Util.encode(seq);
		// update short url - https://brunch.co.kr/@anonymdevoo/37
		urlModel.setShortUrl(shortUrl);
		urlModel = saveUrl(urlModel);
		// 원래는 delete 안해줘도 set할 때 값이 덮어씌워지지만, 좀 더 안전하게 쓰고 싶을 경우, delete 후 set
		redisTemplate.delete(shortUrl);  // 해당 키가 존재하지 않으면 그냥 아무 일도 하지 않고 false를 반환
		redisTemplate.opsForValue().set(shortUrl, urlModel.getOriginalUrl());  // 새 값 저장

		return shortUrl;
	}

	private UrlModel makeUrlModel(Url requestUrl) {
		UrlModel urlModel = new UrlModel();
		urlModel.setUrlName(requestUrl.getUrlName());
		urlModel.setOriginalUrl(modifyUrl(requestUrl.getOriginalUrl()));
		urlModel.setRegId(requestUrl.getRegId());
		urlModel.setRegDate(LocalDateTime.now());
		return urlModel;
	}

	private String modifyUrl(String url) {
		if(url.startsWith("https://") || url.startsWith("http://")){
			return url;
		}
		return "https://" + url;
	}

	public UrlModel saveUrl(UrlModel urlModel){
		return urlMappingRepository.save(urlModel);
	}

}
