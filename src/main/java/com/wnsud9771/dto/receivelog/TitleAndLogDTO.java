package com.wnsud9771.dto.receivelog;

import lombok.Data;

@Data
public class TitleAndLogDTO { //컨슈밍으로 받아온 로그의 타이틀 서비스에서 따와서 log랑같이 entity(db)에 저장하기위한 dto 
							  // title_and_log entity -> dto
	private String title; // log 데이터의 제목
	private String logdata; // 컨슈밍으로 들어온 로그
}
