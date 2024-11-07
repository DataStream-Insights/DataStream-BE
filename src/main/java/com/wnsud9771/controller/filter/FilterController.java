package com.wnsud9771.controller.filter;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.filter.FilterItemDTO;
import com.wnsud9771.dto.filter.management.FilterManagementDTO;
import com.wnsud9771.dto.filter.management.ResponseFilterManagementDTO;
import com.wnsud9771.service.filter.FilterManagementService;
import com.wnsud9771.service.filter.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filter")
@Slf4j
public class FilterController {
	private final ItemService itemService;
	private final FilterManagementService filterManagementService;

    @GetMapping("/log-items")
    @Operation(summary = "아이템 목록 조회", description = "전체 아이템 목록을 조회합니다.")
    public ResponseEntity<List<FilterItemDTO>> getItems() {
    	log.info("#####################log-items 보내는것:    {}",itemService.getAllItems());
        return ResponseEntity.ok(itemService.getAllItems());
    }
    
    @PostMapping("/savefilter")
    @Operation(summary = "필터링의 행동 정의 설정부분 데이터 저장하는 api", description = "필터링의 행동 정의 설정부분 데이터 저장하는 api")
    public ResponseEntity<ResponseFilterManagementDTO> savefilters(@RequestBody ResponseFilterManagementDTO responseFilterManagementDTO){
   
    	return ResponseEntity.ok(filterManagementService.createFilterManagement(responseFilterManagementDTO));
    }
    
    @GetMapping("/filtermanagement")
    @Operation(summary = "아이템 목록 조회", description = "전체 아이템 목록을 조회합니다.")
    public ResponseEntity<List<FilterManagementDTO>> findAllFilterManagements(){
    	
    	return ResponseEntity.ok(filterManagementService.findManagements()); 
    }
    
}
