package com.wnsud9771.dto.format.parsing;

import lombok.Data;

@Data
public class ExistLogItemDTO {
	private String name; // format field의 필드명
	private String value; // 아이템 컨텐츠 예시 == formatfiled의 아이템 컨텐츠 예시
	private String path; // 현재 노드의 전체 경로

	private String item_alias; // 아이템 표시 명
	private String item_explain;// 아이템 설명 
	private String item_type; // 아이템 타입
	private boolean hasChild; // 하위 노드 존재 여부
}
