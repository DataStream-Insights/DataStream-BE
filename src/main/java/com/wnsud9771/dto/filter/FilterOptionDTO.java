package com.wnsud9771.dto.filter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FilterOptionDTO {
    private Long id; //옵션의 고유 ID
    private String value;    // 실제 값 ("equals")
    private String label;    // 화면에 표시될 값 ("=")
}
