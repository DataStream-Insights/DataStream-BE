package com.wnsud9771.controller.format;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.format.TitleDTO;
import com.wnsud9771.dto.format.parsing.LogFindByTitleDTO;
import com.wnsud9771.dto.format.parsing.LogItemDTO;
import com.wnsud9771.dto.format.parsing.LogParseDTO;
import com.wnsud9771.dto.format.parsing.SearchNodeDTO;
import com.wnsud9771.dto.format.parsing.SubStringDTO;
import com.wnsud9771.service.format.CallTitleService;
import com.wnsud9771.service.format.LogParsingService;
import com.wnsud9771.service.log.ChangeToLogPlusSubStringService;
import com.wnsud9771.service.log.FindLogService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/format")
@Slf4j
public class FormatController {

	private final CallTitleService callTitleService;
	private final LogParsingService logParsingService;
	private final FindLogService findLogService;
	private final ChangeToLogPlusSubStringService changeToLogPlusSubStringService;


	@GetMapping("/gettitle") // log 제목 꺼내기
	public ResponseEntity<List<TitleDTO>> getAllTitle() {
		List<TitleDTO> titles = callTitleService.getAllLogTitle();
		// log.info("Titles found: {}", titles);
		return ResponseEntity.ok(titles);

	}


	@PostMapping("/analyze") // 초기 log 제목 + substring 받기 (title, startdepth, enddepth) 두개 넘겨야함
	public ResponseEntity<List<LogItemDTO>> findLogData(@RequestBody SubStringDTO subStringDTO) {
		log.info("받은 title: {}", subStringDTO.getTitle());
		
		LogFindByTitleDTO logFindByTitle = findLogService.findLogByTitle(subStringDTO.getTitle()); //받은 title로 해당 로그 검색후 반환
		
		LogParseDTO logParseDTO = changeToLogPlusSubStringService.changesubdto(logFindByTitle.getLog(), subStringDTO.getStartdepth(), subStringDTO.getEnddepth());
		// 파싱하기위해 logParseDTO형식으로 맞춰주기 
				
		List<LogItemDTO> result = logParsingService.processLogData(logParseDTO); //로그,start,end 넣고 해당 필드와 밸류 반환
		log.info("조회된 field 정보: {}", result);
		return ResponseEntity.ok(result); // key, value, path, haschild 반환
	}
	
	@PostMapping("/analyze/search") // 토글 path 받아서 하위 계층 검색 (title, key, path, haschild ) 넘겨야함
	 public ResponseEntity<List<LogItemDTO>> searchNode(@RequestBody SearchNodeDTO searchNodeDTO) {
        log.info("받은 title: {}, searchKey: {}", searchNodeDTO.getTitle(), searchNodeDTO.getPath());
        
        LogFindByTitleDTO logFindByTitle = findLogService.findLogByTitle(searchNodeDTO.getTitle());
        
        List<LogItemDTO> result = logParsingService.parseLog(logFindByTitle.getLog(), searchNodeDTO.getPath());
        log.info("검색된 node 정보: {}", result);
        return ResponseEntity.ok(result); // key, value, path, haschild 반환은 똑같음
    }

}