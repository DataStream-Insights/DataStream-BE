package com.wnsud9771.service.log;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.format.parsing.LogParseDTO;

@Service
public class ChangeToLogPlusSubStringService { //log 파싱하기위해 형식 맞추려는 서비스 
	public LogParseDTO changesubdto(String log, int start, int end) {
		LogParseDTO dto = new LogParseDTO();
		dto.setLog(log);
		dto.setStartdepth(start);
		dto.setEnddepth(end);
		
		return dto;
	}
}
