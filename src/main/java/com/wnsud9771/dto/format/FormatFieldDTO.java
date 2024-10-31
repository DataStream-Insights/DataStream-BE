package com.wnsud9771.dto.format;

import lombok.Data;

@Data
public class FormatFieldDTO {
	
	private String substring; // 서브스트링
	private String substring_start; // 서브스트링 시작
	private String substring_end; // 서브스트링 끝
	private String substring_start_content;// 서브스트링 시작 빈칸 안에 데이터
	private String substring_end_content;// 서브스트링 끝 빈칸 안에 데이터
	private Long substring_start_count; // 서브스트링 시작 맨 오른쪽 카운트
	private Long substring_end_count; // 서브스트링 끝 맨 오른쪽 카운트
	private String file_format; // 파일 형식

}
