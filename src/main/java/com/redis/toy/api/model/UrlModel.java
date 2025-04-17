package com.redis.toy.api.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/***
	해당 annotation이 붙은 클래스는 JPA가 관리하는 것 = DB 테이블을 표현
	속성:
 		Name - JPA에서 사용할 엔티티 이름 지정 (디폴트: 클래스 이름 사용)
	주의사항:
		- 기본 생성자는 필수(JPA가 엔티티 객체 생성 시 기본 생성자 사용)
	 출처: https://ttl-blog.tistory.com/112 [Shin._.Mallang:티스토리]
 ***/

@Entity
@Table(name = "tb_url_mappings")
@AllArgsConstructor
@NoArgsConstructor
public class UrlModel {
	@Id @Column(name = "SEQ")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// IDENTITY 전략 = 기본 키 생성을 데이터베이스에 위임한다. MySQL의 AUTO_ INCREMENT에 해당
	private int seq;

	@Column(name = "URL_NAME", nullable = false)
	private String urlName;

	@Column(name = "ORIGINAL_URL", nullable = false)
	private String originalUrl;

	@Column(name = "SHORT_URL", nullable = true)
	private String shortUrl;

	@Column(name = "REG_ID", nullable = false)
	private String regId;

	@Column(name = "REG_DATE", nullable = false)
	private LocalDateTime regDate;

	@Column(name = "MOD_ID", nullable = true)
	private String modId;

	@Column(name = "MOD_DATE", nullable = true)
	private LocalDateTime modDate;

	public int getSeq() {
		return seq;
	}

	public String getUrlName() {
		return urlName;
	}

	public void setUrlName(String urlName) {
		this.urlName = urlName;
	}

	public String getOriginalUrl() {
		return originalUrl;
	}

	public void setOriginalUrl(String originalUrl) {
		this.originalUrl = originalUrl;
	}

	public String getShortUrl() {
		return shortUrl;
	}

	public void setShortUrl(String shortUrl) {
		this.shortUrl = shortUrl;
	}

	public String getRegId() {
		return regId;
	}

	public void setRegId(String regId) {
		this.regId = regId;
	}

	public LocalDateTime getRegDate() {
		return regDate;
	}

	public void setRegDate(LocalDateTime regDate) {
		this.regDate = regDate;
	}

	public String getModId() {
		return modId;
	}

	public void setModId(String modId) {
		this.modId = modId;
	}

	public LocalDateTime getModDate() {
		return modDate;
	}

	public void setModDate(LocalDateTime modDate) {
		this.modDate = modDate;
	}
}
