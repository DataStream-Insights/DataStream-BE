package com.wnsud9771.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.Category1DTO;
import com.wnsud9771.dto.Category2DTO;
import com.wnsud9771.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {
	private final CategoryService categoryService;
	
	  @GetMapping("/category1")
	    public ResponseEntity<List<Category1DTO>> getAllCategory1() {
	        List<Category1DTO> categories = categoryService.getAllCategory1();
	        return ResponseEntity.ok(categories);
	    }

	    @GetMapping("/category2/{category1Id}")
	    public ResponseEntity<List<Category2DTO>> getCategory2ByCategory1Id(
	            @PathVariable Long category1Id) {
	        List<Category2DTO> categories = categoryService.getCategory2ByCategory1Id(category1Id);
	        return ResponseEntity.ok(categories);
	    }
}
