package com.wnsud9771.controller.filter;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.filter.FilterItemDTO;
import com.wnsud9771.service.filter.ItemService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filter")
@Slf4j
@Tag(name = "Items", description = "아이템 관리 API")
public class FilterController {
	private final ItemService itemService;

    @GetMapping("/log-items")
    @Operation(summary = "아이템 목록 조회", description = "전체 아이템 목록을 조회합니다.")
    public ResponseEntity<List<FilterItemDTO>> getItems() {
    	log.info("####################log-items 보내는것:    {}",itemService.getAllItems());
        return ResponseEntity.ok(itemService.getAllItems());
    }
}
