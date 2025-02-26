package com.redis.toy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.redis.toy.model.UrlModel;

@Repository
public interface UrlMappingRepository extends JpaRepository<UrlModel, Integer> {
	UrlModel findBySeq(int seq);
}
