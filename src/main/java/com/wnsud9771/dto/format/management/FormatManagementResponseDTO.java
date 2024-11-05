package com.wnsud9771.dto.format.management;

import java.util.List;

import lombok.Data;

@Data
public class FormatManagementResponseDTO {
	private Long id;
    private int start;
    private int end;
    private String formatname;
    private String formatID;
    private String formatexplain;
    private String filetype;
    private List<FormatSetResponseDTO> formatSets;
}
