package com.wnsud9771.service.format;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.format.TitleDTO;
import com.wnsud9771.entity.Formatentity.TitleAndLog;
import com.wnsud9771.reoisitory.format.TitleAndLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CallTitleService {
	private final TitleAndLogRepository titleAndLogRepository;
	
//	// -----------------log title 꺼내기-----------------
		public List<TitleDTO> getAllLogTitle() {
	        return titleAndLogRepository.findAll().stream()
	                .map(this::convertToTitleDTO)
//	                .distinct()
	                .collect(Collectors.toList());
	        
	    }
	
	private TitleDTO convertToTitleDTO(TitleAndLog titleAndLog) {
        TitleDTO dto = new TitleDTO();
        dto.setTitle(titleAndLog.getTitle());
        return dto;
    }
	
	//------------------------------------------------------
	

}
