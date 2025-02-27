package com.redis.toy.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.toy.api.model.UrlModel;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlModel, Integer> {
	UrlModel findBySeq(int seq);
}
