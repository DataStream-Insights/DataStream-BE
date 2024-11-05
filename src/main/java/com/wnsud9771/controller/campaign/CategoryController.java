package com.wnsud9771.controller.campaign;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.campaign.Category1DTO;
import com.wnsud9771.dto.campaign.Category2DTO;
import com.wnsud9771.service.Campaign.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;
	
	 @Operation(summary = "캠페인 추가에서 상위 카테고리 전체 검색", description = "전체 아이템 목록을 조회합니다.")
	  @GetMapping("/category1")
	    public ResponseEntity<List<Category1DTO>> getAllCategory1() {
	        List<Category1DTO> categories = categoryService.getAllCategory1();
	        return ResponseEntity.ok(categories);
	    }

	 @Operation(summary = "상위 카테고리에 맞는 하위 카테고리 검색", description = "전체 아이템 목록을 조회합니다.")
	    @GetMapping("/category2/{category1Id}")
	    public ResponseEntity<List<Category2DTO>> getCategory2ByCategory1Id(@PathVariable Long category1Id) {
	        List<Category2DTO> categories = categoryService.getCategory2ByCategory1Id(category1Id);
	        return ResponseEntity.ok(categories);
	    }
}
