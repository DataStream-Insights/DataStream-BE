package com.wnsud9771.controller.filter;

import java.util.List;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.filter.FilterItemDTO;
import com.wnsud9771.dto.filter.management.FilterManagementDTO;
import com.wnsud9771.dto.filter.management.ResponseFilterManagementDTO;
import com.wnsud9771.dto.filter.management.search.FindManagementByIdDTO;
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
	private final ApplicationEventPublisher eventPublisher;

    @GetMapping("/log-items")
    @Operation(summary = "아이템 목록 조회", description = "전체 아이템 목록을 조회합니다.")
    public ResponseEntity<List<FilterItemDTO>> getItems() {
    	log.info("#####################log-items 보내는것:    {}",itemService.getAllItems());
        return ResponseEntity.ok(itemService.getAllItems());
    }
    
    @PostMapping("/savefilter")
    @Operation(summary = "(모든필드 비어있으면 안됨) 필터링의 행동 정의 설정부분 데이터 저장하는 api", description = "필터링의 행동 정의 설정부분 데이터 저장하는 api")
    public ResponseEntity<ResponseFilterManagementDTO> saveonlyfilters(@RequestBody ResponseFilterManagementDTO responseFilterManagementDTO){
    	log.info("save filter dto {}", responseFilterManagementDTO);
    	
    	ResponseFilterManagementDTO created = filterManagementService.createonlyFilterManagement(responseFilterManagementDTO);
    	
    	//토픽 생성 안함 지금
    	//eventPublisher.publishEvent(new FilterCreatedEvent(this, created.getFiltermanage_id(),formatID, campaignId));
    	
    	return ResponseEntity.ok(created);
    }
    
    @GetMapping("/filtermanagement")
    @Operation(summary = "필터 관리화면 목록 모두 조회", description = "전체 아이템 목록을 조회합니다.")
    public ResponseEntity<List<FilterManagementDTO>> findAllFilterManagements(){
    	
    	return ResponseEntity.ok(filterManagementService.findonlyManagements()); //캠페인id,포맷id받아서 해당 캠페인의 포맷으로 
    }
    
    
    
    @GetMapping("/filtermanagement/{id}")
    @Operation(summary = "모든 필터 관리화면 목록 상세 조회", description = "id만 받아서.")
    public ResponseEntity<FindManagementByIdDTO> findallmanagementbyid(@PathVariable Long id){
    	FindManagementByIdDTO findManagementById = filterManagementService.findById(id);
    	return ResponseEntity.ok(findManagementById);
    }
    

    
    @GetMapping("/{campaignId}/{formatID}/filtermanagement")
    @Operation(summary = "필터 관리화면 목록 조회", description = "전체 아이템 목록을 조회합니다.")
    public ResponseEntity<List<FilterManagementDTO>> findAllFilterManagements(@PathVariable String campaignId,@PathVariable String formatID){
    	
    	return ResponseEntity.ok(filterManagementService.findManagements(campaignId,formatID)); //캠페인id,포맷id받아서 해당 캠페인의 포맷으로 
    }
    
    
    @GetMapping("/{campaignId}/{formatID}/filtermanagement/{id}")
    @Operation(summary = "필터 관리화면 목록 상세 조회", description = "선택한 아이템 목록을 조회합니다.")
    public ResponseEntity<FindManagementByIdDTO> findmanagementbyid(@PathVariable String campaignId,@PathVariable String formatID, @PathVariable Long id){
    	FindManagementByIdDTO findManagementById = filterManagementService.findById(id);
    	return ResponseEntity.ok(findManagementById);
    }
    
}
