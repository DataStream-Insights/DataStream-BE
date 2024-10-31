package com.wnsud9771.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.format.TitleDTO;
import com.wnsud9771.service.format.CallFieldNameService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/format")
@Slf4j
public class FormatController {
	
	private final CallFieldNameService callFieldNameService;
	
	@GetMapping("/gettitle") // log 제목 꺼내기
    public ResponseEntity<List<TitleDTO>> getAllTitle() {
        List<TitleDTO> titles = callFieldNameService.getAllTitle();
        log.info("Titles found: {}", titles);
        return ResponseEntity.ok(titles);
        
    }
	
//	@PostMapping("/posttitle") // log 제목 받기
//	public ResponseEntity<TitleDTO> findLogData(@RequestBody TitleDTO titleDTO) {
//		return ResponseEntity.ok(callFieldNameService.findLogData(titleDTO));
//	}
//	
//	@GetMapping("/category2/{category1Id}") //log 데이터 보내기
//    public ResponseEntity<List<Category2DTO>> getCategory2ByCategory1Id(@PathVariable Long category1Id) {
//        List<Category2DTO> categories = categoryService.getCategory2ByCategory1Id(category1Id);
//        return ResponseEntity.ok(categories);
//    }
}
