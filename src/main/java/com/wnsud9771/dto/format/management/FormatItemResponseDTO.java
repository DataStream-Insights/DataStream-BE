package com.wnsud9771.dto.format.management;

import lombok.Data;

@Data
public class FormatItemResponseDTO { //실제 포맷아이템 dto
    private String fieldName;
    private String itemAlias;
    private String itemExplain;
    private String itemType;
    private String itemContent;
    private String path;
}
