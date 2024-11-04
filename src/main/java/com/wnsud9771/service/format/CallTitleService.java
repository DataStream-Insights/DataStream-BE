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
	
	// -----------------log title 꺼내기-----------------
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
	
	//--------------------log title 받아와서 검색후 밸류들 logitem에저장---------------------
	public List<LogItemDTO> findLogData(TitleDTO titleDTO) {
        // 1. title로 데이터 조회
        List<FieldName> fieldNames = fieldNameRepository.findByTitle(titleDTO.getTitle());
        
        // 2. LogItemDTO 리스트로 변환해서 반환
        return fieldNames.stream()
            .map(fieldName -> LogItemDTO.builder()
                .name(fieldName.getFieldname())
                .value(fieldName.getItemcontentsex())
                .build())
            .collect(Collectors.toList());
    }
	
}
