package com.wnsud9771.service.filter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.filter.management.FilterManagementDTO;
import com.wnsud9771.dto.filter.management.ResponseFilterManagementDTO;
import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.reoisitory.filter.FilterManagementRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterManagementService {
	private final FilterManagementRepository filterManagementRepository;
	
	// 필터 관리 생성
	public  ResponseFilterManagementDTO createFilterManagement( ResponseFilterManagementDTO  responseFilterManagementDTO){
		ResponseFilterManagementDTO dto = new ResponseFilterManagementDTO();
		
		
		return dto;
	}
	
	
	
	
	
	
	// 필터 관리 모두 검색
	public List<FilterManagementDTO> findManagements() {
		return filterManagementRepository.findAll().stream().map(this::managementconvertToDTO).collect(Collectors.toList());
	}
	
	//필터 관리 엔티티 -> dto
	private FilterManagementDTO managementconvertToDTO(FilterManagement entity) {
		FilterManagementDTO dto = new FilterManagementDTO();
		dto.setId(entity.getId());
		dto.setFilterName(entity.getFilter_name());
		dto.setFilterManageId(entity.getFilter_manage_id());
		return dto;
	}
	
}
