package com.wnsud9771.controller.format;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

import io.swagger.v3.oas.annotations.Operation;
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
//	private final FormatManagementService formatManagementService;

	 @Operation(summary = "로그파일 제목 가져오기", description = "전체 아이템 목록을 조회합니다.")
	@GetMapping("/gettitle") // log 제목 꺼내기
	public ResponseEntity<List<TitleDTO>> getAllTitle() {
		List<TitleDTO> titles = callTitleService.getAllLogTitle();
		// log.info("Titles found: {}", titles);
		return ResponseEntity.ok(titles);

	}

	 @Operation(summary = "필터 적용 버튼 눌렀을때", description = "로그 서브스트링만 처리후 처음 반환")
	@PostMapping("/analyze") // 초기 log 제목 + substring 받기 (title, startdepth, enddepth) 넘겨야함
	public ResponseEntity<List<LogItemDTO>> findLogData(@RequestBody SubStringDTO subStringDTO) {
		log.info("받은 title: {}", subStringDTO.getTitle());
		
		LogFindByTitleDTO logFindByTitle = findLogService.findLogByTitle(subStringDTO.getTitle()); //받은 title로 해당 로그 검색후 반환
		
		LogParseDTO logParseDTO = changeToLogPlusSubStringService.changesubdto(logFindByTitle.getLog(), subStringDTO.getStartdepth(), subStringDTO.getEnddepth());
		// 파싱하기위해 logParseDTO형식으로 맞춰주기 
				
		List<LogItemDTO> result = logParsingService.processLogData(logParseDTO); //로그,start,end 넣고 해당 필드와 밸류 반환
		log.info("조회된 field 정보: {}", result);
		return ResponseEntity.ok(result); // key, value, path, haschild 반환
	}
	
	 @Operation(summary = "필드의 토글 눌렀을때", description = "처음 서브스트링 반환후에 토글버튼눌러서 그 해당하는 필드의 path넘겨서 하위 계층들 검색하는 api")
	@PostMapping("/analyze/search") // 토글 path 받아서 하위 계층 검색 (title, key, path, haschild ) 넘겨야함
	 public ResponseEntity<List<LogItemDTO>> searchNode(@RequestBody SearchNodeDTO searchNodeDTO) {
        log.info("받은 title: {}, searchKey: {}", searchNodeDTO.getTitle(), searchNodeDTO.getPath());
        
        LogFindByTitleDTO logFindByTitle = findLogService.findLogByTitle(searchNodeDTO.getTitle());
        
        List<LogItemDTO> result = logParsingService.parseLog(logFindByTitle.getLog(), searchNodeDTO.getPath());
        log.info("검색된 node 정보: {}", result);
        return ResponseEntity.ok(result); // key, value, path, haschild 반환은 똑같음
    }
	
//	@PostMapping("/formatfields") // 필드 설정 정보 저장하는 api

//	@GetMapping("/management") // formatmanagement list 불러오기
//	public ResponseEntity<List<FormatManagementResponse>> getFormatManagement(@PathVariable Long id){
//		
//		List<FormatManagementResponse> result = formatManagementService.findlistManagement(id);
//		
//		return ResponseEntity.ok(result);
//	}
//	
//	@GetMapping("/management/{id}")// formatmanagement 에서 클릭하면 필드 설정 정보 화면의 데이터들 띄워주기
//	
//	
}