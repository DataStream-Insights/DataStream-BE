package com.wnsud9771.entity.Formatentity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class TitleAndLog { // 맨처음 들어온 로그를 타이틀부분 빼내서 그 타이틀과 해당로그 db
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String title; // log 데이터의 제목
	private String logdata; // 컨슈밍으로 들어온 로그

}
