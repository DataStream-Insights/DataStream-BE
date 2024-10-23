package com.wnsud9771.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.Category1DTO;
import com.wnsud9771.dto.Category2DTO;
import com.wnsud9771.entity.Category1;
import com.wnsud9771.entity.Category2;
import com.wnsud9771.reoisitory.Category1Repository;
import com.wnsud9771.reoisitory.Category2Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
	private final Category1Repository category1Repository;
    private final Category2Repository category2Repository;

    public List<Category1DTO> getAllCategory1() {
        return category1Repository.findAll().stream()
                .map(this::convertToCategory1DTO)
                .collect(Collectors.toList());
    }

    public List<Category2DTO> getCategory2ByCategory1Id(Long category1Id) {
        Category1 category1 = category1Repository.findById(category1Id)
                .orElseThrow(() -> new RuntimeException("Category1 not found"));
        
        return category2Repository.findByCategory1(category1).stream()
                .map(this::convertToCategory2DTO)
                .collect(Collectors.toList());
    }

    private Category1DTO convertToCategory1DTO(Category1 category1) {
        Category1DTO dto = new Category1DTO();
        dto.setId(category1.getId());
        dto.setCategory1(category1.getCategory1());
        return dto;
    }

    private Category2DTO convertToCategory2DTO(Category2 category2) {
        Category2DTO dto = new Category2DTO();
        dto.setId(category2.getId());
        dto.setCategory2(category2.getCategory2());
        return dto;
    }
}
