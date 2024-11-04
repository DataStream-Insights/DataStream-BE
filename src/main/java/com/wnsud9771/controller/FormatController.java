package com.wnsud9771.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.format.LogItemDTO;
import com.wnsud9771.dto.format.SubStringDTO;
import com.wnsud9771.dto.format.TitleDTO;
import com.wnsud9771.service.format.CallTitleService;
import com.wnsud9771.service.format.LogParsingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/format")
@Slf4j
public class FormatController {

	private final CallTitleService callTitleService;
	private final LogParsingService logParsingService;


	@GetMapping("/gettitle") // log 제목 꺼내기
	public ResponseEntity<List<TitleDTO>> getAllTitle() {
		List<TitleDTO> titles = callTitleService.getAllLogTitle();
		// log.info("Titles found: {}", titles);
		return ResponseEntity.ok(titles);

	}

//	//서브 스트링 정보 받기
//	@PostMapping("/substring")
//    public ResponseEntity<String> parseLog(@RequestParam int start, @RequestParam int end) {
//        return ResponseEntity.ok(logParsingService.parseLog(start, end));
//    }

	@PostMapping("/posttitle") // log 제목 받기
	public ResponseEntity<List<LogItemDTO>> findLogData(@RequestBody SubStringDTO subStringDTO) {
		log.info("받은 title: {}", subStringDTO.getTitle());
		List<LogItemDTO> result = logParsingService.findLogData(titleDTO);
		log.info("조회된 field 정보: {}", result);
		return ResponseEntity.ok(result);
	}

//	@PostMapping("/psttitle2")
//	public ResposeEntity<List<LogItemDTO>> findandparseLogData(@RequestBody TitleDTO titleDTO) {
//		
//	}

//	@GetMapping("/getfield/{field}") //log 데이터 보내기
//    public ResponseEntity<List<Category2DTO>> getCategory2ByCategory1Id(@PathVariable String field) {
//        List<Category2DTO> categories = categoryService.getCategory2ByCategory1Id(category1Id);
//        return ResponseEntity.ok(categories);
//    }
}
