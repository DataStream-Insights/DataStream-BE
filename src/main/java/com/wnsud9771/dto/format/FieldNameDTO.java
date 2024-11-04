package com.wnsud9771.dto.format;

import lombok.Data;

@Data
public class FieldNameDTO {
	
	private Long id;
	private String title; //log 데이터의 제목
	private String fieldname; // format field의 필드명
	private String itemcontentsex; //아이템 컨텐츠 예시 == formatfiled의 아이템 컨텐츠 예시
}
