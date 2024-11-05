package com.wnsud9771.dto.format.management;

import lombok.Data;

@Data
public class FormatItemResponseDTO {
	private Long id;
    private String fieldName;
    private String itemAlias;
    private String itemExplain;
    private String itemType;
    private String itemContent;
}
