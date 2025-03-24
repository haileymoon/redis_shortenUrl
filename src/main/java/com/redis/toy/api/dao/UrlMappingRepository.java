package com.redis.toy.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import com.redis.toy.api.model.UrlModel;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlModel, Integer> {
	List<UrlModel> findByShortUrl(String shortUrl);
}
