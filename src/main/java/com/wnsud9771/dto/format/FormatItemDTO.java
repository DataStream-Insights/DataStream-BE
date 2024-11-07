package com.wnsud9771.dto.format;

import lombok.Data;

@Data
public class FormatItemDTO { //format_item entity -> dto
	
	private String fieldName;
    private String itemAlias;
    private String itemExplain;
    private String itemType;
    private String itemContent;
    private String path;

}
