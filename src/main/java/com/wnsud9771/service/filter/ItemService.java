package com.wnsud9771.service.filter;

import com.wnsud9771.dto.filter.*;
import com.wnsud9771.entity.FIlterentity.*;
import com.wnsud9771.reoisitory.filter.*;

import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.cache.spi.support.AbstractReadWriteAccess.Item;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemService {
    
    private final FilterItemRepository filterItemRepository;
    
    public List<FilterItemDTO> getAllItems() {
        List<FilterItem> items = filterItemRepository.findAll();
        return items.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    
    private FilterItemDTO convertToDto(FilterItem item) {
    	FilterItemDTO dto = new FilterItemDTO();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setType(item.getType());
        return dto;
    }
}
