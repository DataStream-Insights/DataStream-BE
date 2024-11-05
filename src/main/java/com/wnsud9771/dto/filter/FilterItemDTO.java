package com.wnsud9771.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FilterItemDTO {
    private Long id;
    private String name;
    private String type;
}

//import lombok.Data;
//
//@Data
//public class FilterItemDTO {
//	
//	private String item_name; //아이템 명
//	private String item_alias; //아이템 별명
//	private String item_explain; //아이템 설명
//	private String item_type; //TYPE
//	private String item_contents_ex; //아이템 컨텐츠 예시
//}
