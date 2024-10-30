package com.wnsud9771.entity.FIlterentity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class FilterItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String item_name; //아이템 명
	private String item_alias; //아이템 별명
	private String item_explain; //아이템 설명
	private String item_type; //TYPE
	private String item_contents_ex; //아이템 컨텐츠 예시
	
}
