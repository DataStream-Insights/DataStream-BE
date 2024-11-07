package com.wnsud9771.service.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wnsud9771.dto.filter.filtering.FilterOptionDTO;
import com.wnsud9771.entity.FIlterentity.Operation;
import com.wnsud9771.entity.item.FormatItem;
import com.wnsud9771.reoisitory.filter.OperationRepository;
import com.wnsud9771.reoisitory.item.FormatItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FilterOptionsService {
    private final FormatItemRepository formatItemRepository;
    private final OperationRepository operationRepository;

    // ID 옵션 조회 (FormatItem의 itemAlias 사용)
    public List<FilterOptionDTO> getIdOptions() {
        return formatItemRepository.findAll().stream()
            .map(this::convertToIdOption)
            .collect(Collectors.toList());
    }

    // 연산자 옵션 조회
    public List<FilterOptionDTO> getOperatorOptions() {
        return operationRepository.findAll().stream()
            .map(this::convertToOperatorOption)
            .collect(Collectors.toList());
    }

    //FormatItem을 옵션 DTO로 변환
    private FilterOptionDTO convertToIdOption(FormatItem item) {
        FilterOptionDTO dto = new FilterOptionDTO();
        dto.setId(item.getId());
        dto.setValue(item.getFieldName());     // 실제 값으로 fieldName 사용 - 필요 시 원하는 방식으로 수정
        dto.setLabel(item.getItemAlias());     // 화면에 표시될 값으로 itemAlias 사용
        return dto;
    }

    //operatiion을 연산자 옵션 DTO로 변환
    private FilterOptionDTO convertToOperatorOption(Operation operation) {
        FilterOptionDTO dto = new FilterOptionDTO();
        dto.setId(operation.getId());
        dto.setValue(operation.getOperation());
        
        // operation 값을 화면에 표시될 형태로 변환
        String label = switch(operation.getOperation()) {
            case "equals" -> "=";
            case "not_equals" -> "!=";
            case "greater_than" -> ">";
            case "less_than" -> "<";
            case "greater_equals" -> ">=";
            case "less_equals" -> "<=";
            default -> operation.getOperation();
        };
        dto.setLabel(label);
        
        return dto;
    }
}