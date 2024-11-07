package com.wnsud9771.controller.filter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.filter.filtering.FilterOptionDTO;
import com.wnsud9771.service.filter.FilterOptionsService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/filter")
@Slf4j
public class FilterOptionsController {
    private final FilterOptionsService filterOptionsService;

    @GetMapping("/options")
    public ResponseEntity<Map<String, List<FilterOptionDTO>>> getFilterOptions() {
        log.info("Fetching filter options");
        
        //ID 옵션과 연산자 옵션을 모두 조회
        Map<String, List<FilterOptionDTO>> options = new HashMap<>();
        
        //ID 옵션 조회(FormatItem의 itemAlias 기반)
        List<FilterOptionDTO> idOptions = filterOptionsService.getIdOptions();
        //연산자 옵션 조회 (Operation 테이블 기반)
        List<FilterOptionDTO> operatorOptions = filterOptionsService.getOperatorOptions();
        
        //조회된 옵션들을 Map에 담아서 반환
        options.put("idOptions", idOptions);
        options.put("operatorOptions", operatorOptions);
        
        log.info("Returning {} ID options and {} operator options", 
            idOptions.size(), operatorOptions.size());
            
        return ResponseEntity.ok(options);
    }
}