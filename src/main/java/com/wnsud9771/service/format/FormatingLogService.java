package com.wnsud9771.service.format;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wnsud9771.dto.format.parsing.FormatingDTO;
import com.wnsud9771.dto.format.parsing.LogValueDTO;
import com.wnsud9771.dto.format.parsing.ParsedLogDTO;
import com.wnsud9771.dto.receivelog.LogDTO;
import com.wnsud9771.dto.receivelog.SendLogDTO;
import com.wnsud9771.entity.Formatentity.FormatManagement;
import com.wnsud9771.entity.Formatentity.TitleAndLog;
import com.wnsud9771.reoisitory.format.FormatManagementRepository;
import com.wnsud9771.reoisitory.format.TitleAndLogRepository;
import com.wnsud9771.service.sendkafka.FormatingSendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FormatingLogService {
	private final FormatManagementRepository formatManagementRepository;
	private final LogParsingService logParsingService;
	private final FormatingSendService formatingSendService;
	private final TitleAndLogRepository titleAndLogRepository;

	// topicname과 포맷팅된 로그를 send함수 호출
	public void sendFormating(FormatingDTO formatingDTO) {

		SendLogDTO dto = new SendLogDTO();
		dto.setTitle(formatingDTO.getTopicname());
		dto.setContents(formatingDTO.getFormatingLog());
		
		//api로 보내기 일단 막아둠
		formatingSendService.sendLogData(dto);

	}

	//id로 해당 포맷 찾는 함수 + 파싱 함수 불러서 처리후 dto로 감싸서 sendformating으로 보냄
	private void formatingLogById(String formatid) {

		// 일단 formatname과 path들 먼저 찾기
		FormatManagement format = formatManagementRepository.findByFormatID(formatid)
				.orElseThrow(() -> new IllegalArgumentException(" formatid를 찾을수 없습니다: " + formatid));

		List<String> paths = format.getFormatSets().stream().map(formatSet -> formatSet.getFormatItem().getPath())
				.collect(Collectors.toList());
		
		
		FormatingDTO dto = new FormatingDTO();
		dto.setTopicname(format.getFormatname());
		dto.setFormatingLog(parsingLog(paths).getParsedLog());

		sendFormating(dto);

	}
	
	private ParsedLogDTO parsingLog(List<String> paths) {
		ParsedLogDTO dto = new ParsedLogDTO();
		List<String>path = paths;
		 ObjectMapper mapper = new ObjectMapper();
		 Map<String, String> resultMap = new HashMap<>();
		
		if(!path.isEmpty()) {
			List<LogValueDTO> values = logParsingService.extractValuesByPaths(testLog(4L).getLog_data(),path);
		
		 for (LogValueDTO valueDto : values) {
	            resultMap.put(valueDto.getPath(), valueDto.getValue());
	        }
	        try {
	            // Map을 JSON 문자열로 변환
	            String jsonResult = mapper.writeValueAsString(resultMap);
	            dto.setParsedLog(jsonResult);
	            log.info("****포매팅 한 로그 {}", jsonResult);
	        } catch (JsonProcessingException e) {
	            throw new RuntimeException("포매팅 JSON 변환 중 오류가 발생했습니다.", e);
	        }
	    }
	
		return dto; 
	}
	
	//임시로 로그 가져오기
	private LogDTO testLog(Long id) {
		LogDTO dto = new LogDTO();
	TitleAndLog titleAndLog = titleAndLogRepository.findLogDataById(id);
	String logData = titleAndLog.getLogData();
		dto.setLog_data(logData);
		return dto;
	}
	
	
//	//test위해 실행시 포맷팅 하기
//	@PostConstruct
//	private void TestFormating() {
//		String formatid = "FMT241107817";
//		formatingLogById(formatid);
//	}
//	
	@EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void init() {
        formatingLogById("FMT241107015");
    }
	
	
	//

	// 프론트에서 formatmanagement id 넘기면 그 id 를 기반으로 db의 저장된 정보 찾기
	// formatname은 FormatingDTO 의 topicname 으로 저장
	// 로그 파싱하는건 FormatingDTO의 formatingLOG 로 저장
	// 로그 파싱은 path 기반으로 찾아서 해당 path의 path와 밸류를 한 쌍으로 저장 하기
	// 지금은 그냥 단순하게 샘플로그를 포맷팅하는것만
}
