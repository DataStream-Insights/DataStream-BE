package com.wnsud9771.dto.format;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogItemDTO {
	private String name; // format field의 필드명
	private String value; //아이템 컨텐츠 예시 == formatfiled의 아이템 컨텐츠 예시
}
