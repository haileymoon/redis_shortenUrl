package com.redis.toy.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Configuration
@EnableCaching
public class CacheConfig {
	// 빈으로 등록하는 이유: 스프링의 캐싱 mechanism이 default inmemory cache가 아니라 이걸 써야하는걸 인지해야하기때문
	@Bean
	public RedisCacheManager cacheManager(RedisConnectionFactory redisConnectionFactory){
		// Uses the auto-configured LettuceConnectionFactory -> spring-boot-starter-data-redis
		RedisCacheConfiguration cacheConfig = RedisCacheConfiguration.defaultCacheConfig()
			.entryTtl(Duration.ofMinutes(1));
		return RedisCacheManager.builder(redisConnectionFactory)
			.cacheDefaults(cacheConfig)
			.build();
	}
}
