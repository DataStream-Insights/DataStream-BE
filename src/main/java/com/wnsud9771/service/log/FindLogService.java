package com.wnsud9771.service.log;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.format.parsing.LogFindByTitleDTO;
import com.wnsud9771.entity.Formatentity.TitleAndLog;
import com.wnsud9771.reoisitory.format.TitleAndLogRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindLogService {
	private final TitleAndLogRepository titleAndLogRepository;

//	  public LogFindByTitleDTO findLogByTitle(String title) {
//		  LogFindByTitleDTO dto = new LogFindByTitleDTO();
//		  
//	        String logs = titleAndLogRepository.findByTitle(title)
//	        		.stream()
//	        		.findFirst()
//	        		.map(TitleAndLog::getLogData)
//	        		.orElse("로그를 찾을 수 없습니다.");
//	        
//	        dto.setLog(logs);
//	        
//	        return dto;
//	    }
//	  
	public LogFindByTitleDTO findLogByTitle(String title) {
		TitleAndLog titleAndLog = titleAndLogRepository.findByTitle(title)
				.orElseThrow(() -> new RuntimeException("해당 제목의 로그를 찾을 수 없습니다: " + title));

		LogFindByTitleDTO dto = new LogFindByTitleDTO();
		dto.setLog(titleAndLog.getLogData());

		return dto;
	}

}
