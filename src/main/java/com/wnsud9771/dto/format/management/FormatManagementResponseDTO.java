package com.wnsud9771.dto.format.management;

import java.util.List;

import lombok.Data;

@Data
public class FormatManagementResponseDTO {
	
    private int start; //서브스트링 시작
    private int end; //서브스트링 끝 
    private String formatname; //포맷 이름
    private String formatID; //포맷 아이디 
    private String formatexplain;// 포맷 설명
    private List<FormatSetResponseDTO> formatSets; //포맷 필드 dto
}
