package com.wnsud9771.service.log;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.format.parsing.LogFindByTitleDTO;
import com.wnsud9771.dto.format.parsing.LogParseDTO;
import com.wnsud9771.entity.Formatentity.TitleAndLog;
import com.wnsud9771.reoisitory.format.TitleAndLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindLogService {
	  private TitleAndLogRepository titleAndLogRepository;
	  
	  public LogFindByTitleDTO findLogByTitle(String title) {
		  LogFindByTitleDTO dto = new LogFindByTitleDTO();
		  
	        String logs = titleAndLogRepository.findByTitle(title)
	        		.stream()
	        		.findFirst()
	        		.map(TitleAndLog::getLogdata)
	        		.orElse("로그를 찾을 수 없습니다.");
	        
	        dto.setLog(logs);
	        
	        return dto;
	    }
	  
}
