package com.wnsud9771.dto.format.parsing;

import lombok.Data;

@Data
public class FormatingDTO { //포매팅 한 로그의 제목과, 로그 저장 dto
	private String topicname; // == formatname
	private String formatingLog; // 포매팅한 로그
}
