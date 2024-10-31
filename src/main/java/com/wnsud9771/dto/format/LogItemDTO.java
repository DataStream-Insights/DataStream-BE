package com.wnsud9771.dto.format;

import lombok.Data;

@Data
public class LogItemDTO {
	private String field_name; // format field의 필드명
	private String item_contents_ex; //아이템 컨텐츠 예시 == formatfiled의 아이템 컨텐츠 예시
}
