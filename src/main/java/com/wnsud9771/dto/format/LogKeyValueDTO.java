package com.wnsud9771.dto.format;

import lombok.Data;

@Data
public class LogKeyValueDTO {
	
	private String field_name; // format field의 필드명
	private String item_contents_ex; //format field의 컨텐츠 예시
}
