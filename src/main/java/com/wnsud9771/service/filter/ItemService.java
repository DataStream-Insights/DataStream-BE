package com.wnsud9771.service.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wnsud9771.dto.filter.FilterItemDTO;
import com.wnsud9771.entity.item.FormatItem;
import com.wnsud9771.reoisitory.item.FormatItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    
    private final FormatItemRepository formatItemRepository;
    
    public List<FilterItemDTO> getAllItems() {
        List<FormatItem> items = formatItemRepository.findAll();
        return items.stream()	
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private FilterItemDTO convertToDto(FormatItem item) {
    	FilterItemDTO dto = new FilterItemDTO();
        dto.setName(item.getFieldName());
        dto.setNamealias(item.getItemAlias());
        dto.setType(item.getItemType());
        return dto;
    }
}
