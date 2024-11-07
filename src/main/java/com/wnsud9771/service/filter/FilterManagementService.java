package com.wnsud9771.service.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.filter.FiltervalueDTO;
import com.wnsud9771.dto.filter.OperationDTO;
import com.wnsud9771.dto.filter.management.FilterManagementDTO;
import com.wnsud9771.dto.filter.management.FilterSetDTO;
import com.wnsud9771.dto.filter.management.FilterSetListDTO;
import com.wnsud9771.dto.filter.management.FindManagementByIdDTO;
import com.wnsud9771.dto.filter.management.ResponseFilterManagementDTO;
import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.FIlterentity.FilterSet;
import com.wnsud9771.entity.FIlterentity.FilterSetList;
import com.wnsud9771.entity.FIlterentity.Filtervalue;
import com.wnsud9771.entity.FIlterentity.Operation;
import com.wnsud9771.entity.item.FormatItem;
import com.wnsud9771.reoisitory.filter.FilterManagementRepository;
import com.wnsud9771.reoisitory.filter.FilterSetListRepository;
import com.wnsud9771.reoisitory.filter.OperationRepository;
import com.wnsud9771.reoisitory.item.FormatItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterManagementService {
	private final FilterManagementRepository filterManagementRepository;
	private final FormatItemRepository formatItemRepository;
	private final OperationRepository operationRepository;
	private final FilterSetListRepository filterSetListRepository;
	// 필터 관리 생성
	public ResponseFilterManagementDTO createFilterManagement(ResponseFilterManagementDTO dto) {
        FilterManagement filterManagement = new FilterManagement();

        filterManagement.setFilter_name(dto.getFiltername());
        filterManagement.setFilter_manage_id(dto.getFiltermanage_id());

        if (dto.getFilterSetList() != null) {
            FilterSetList filterSetList = new FilterSetList();
            filterSetList.setFilterManagement(filterManagement);  // 양방향 관계 설정
            filterManagement.setFilterSetList(filterSetList);

            if (!dto.getFilterSetList().getFilterSets().isEmpty()) {
                List<FilterSet> filterSets = new ArrayList<>();

                dto.getFilterSetList().getFilterSets().forEach(filterSetDTO -> {
                    FilterSet filterSet = new FilterSet();
                    filterSet.setFilterSetList(filterSetList);  // 양방향 관계 설정

                    // filterset의 andor 설정
                    filterSet.setAndor(filterSetDTO.getAndor());

                    // filtervalue entity에 dto 넣어서 통으로 set
                    if (filterSetDTO.getFiltervalue() != null) {
                        Filtervalue filtervalue = new Filtervalue();
                        filtervalue.setValue(filterSetDTO.getFiltervalue().getValue());
                        filterSet.setFiltervalue(filtervalue);
                        filtervalue.getFilterSets().add(filterSet);  // 양방향 관계 설정
                    }

                    // Operation entity에 dto 넣어서 통으로 set
//                    if (filterSetDTO.getOperation() != null) {
//                        Operation operation = new Operation();
//                        operation.setOperation(filterSetDTO.getOperation().getOperation());
//                        filterSet.setOperation(operation);
//                    }
                    if (filterSetDTO.getOperation() != null) {
                        Operation operation = operationRepository
                            .findByOperation(filterSetDTO.getOperation().getOperation())
                            .orElseThrow(() -> new RuntimeException("Operation not found: " + 
                                filterSetDTO.getOperation().getOperation()));
                        filterSet.setOperation(operation);
                    }


                    // formatitem entity에 dto 넣어서 통으로 set
                    if (filterSetDTO.getResponseItemid() != null) {
                        FormatItem formatItem = formatItemRepository
                                .getReferenceById(filterSetDTO.getResponseItemid());
                        filterSet.setFormatItem(formatItem);
                    }

                    filterSet.setFilterSetList(filterSetList);
                    filterSets.add(filterSet);
                });
                filterSetList.setFilterSets(filterSets);
            }
            
            filterSetList.setFilterManagement(filterManagement);
            filterManagement.setFilterSetList(filterSetList); 
        }
        
        FilterManagement savedFilterManagement = filterManagementRepository.save(filterManagement);
        return convertToResponseDTO(savedFilterManagement);
    }

	// 필터 관리 모두 검색
	public List<FilterManagementDTO> findManagements() {
		return filterManagementRepository.findAll().stream().map(this::managementconvertToDTO)
				.collect(Collectors.toList());
	}

	// 필터 관리 엔티티 -> dto
	private FilterManagementDTO managementconvertToDTO(FilterManagement entity) {
		FilterManagementDTO dto = new FilterManagementDTO();
		dto.setId(entity.getId());
		dto.setFilterName(entity.getFilter_name());
		dto.setFilterManageId(entity.getFilter_manage_id());
		return dto;
	}
	
	//필터 관리 상세조회 id로 검색
		public FindManagementByIdDTO findById(Long id) {
			FindManagementByIdDTO responseDto = new FindManagementByIdDTO();
			
			FilterSetList filterSetList = filterSetListRepository.findByFilterManagementId(id)
	                .orElseThrow(() -> new RuntimeException("###management에서 filterSetList id 잘못 조회"));
			
	        FilterSetListDTO responsefilterSetListDTO = new FilterSetListDTO();
	        
	        responsefilterSetListDTO.setFilterSets(
	                filterSetList.getFilterSets().stream().map(filterSet -> {
	                	
	                        FilterSetDTO dto = new FilterSetDTO();
	                        dto.setAndor(filterSet.getAndor());
	                        dto.setResponseItemid(filterSet.getFormatItem().getId());
	                        
	                        
	                        OperationDTO operationDTO = new OperationDTO();
	                        operationDTO.setOperation(filterSet.getOperation().getOperation());
	                        dto.setOperation(operationDTO);
	                        
	                        FiltervalueDTO filtervalueDTO = new FiltervalueDTO();
	                        filtervalueDTO.setValue(filterSet.getFiltervalue().getValue());
	                        dto.setFiltervalue(filtervalueDTO);
	                        
	                        return dto;
	                    })
	                    .collect(Collectors.toList())
	            );
	        
	        
	        responseDto.setFilterSetList(responsefilterSetListDTO);
	        
	        return responseDto;
		}

	// entity 다시 응답 dto로 변환 
	private ResponseFilterManagementDTO convertToResponseDTO(FilterManagement filterManagement) {
        ResponseFilterManagementDTO responseDTO = new ResponseFilterManagementDTO();
        
        responseDTO.setFiltername(filterManagement.getFilter_name());
        responseDTO.setFiltermanage_id(filterManagement.getFilter_manage_id());
        
        if (filterManagement.getFilterSetList() != null) { 
            FilterSetListDTO filterSetListDTO = new FilterSetListDTO();
            
            if (filterManagement.getFilterSetList().getFilterSets() != null && 
                !filterManagement.getFilterSetList().getFilterSets().isEmpty()) {
                List<FilterSetDTO> filterSetDTOs = filterManagement.getFilterSetList().getFilterSets().stream()
                    .map(filterSet -> {
                        FilterSetDTO filterSetDTO = new FilterSetDTO();
                        
                        filterSetDTO.setAndor(filterSet.getAndor());
                        
                        if (filterSet.getFiltervalue() != null) {
                            FiltervalueDTO filtervalueDTO = new FiltervalueDTO();
                            filtervalueDTO.setValue(filterSet.getFiltervalue().getValue());
                            filterSetDTO.setFiltervalue(filtervalueDTO);
                        }
                        
                        if (filterSet.getOperation() != null) {
                            OperationDTO operationDTO = new OperationDTO();
                            operationDTO.setOperation(filterSet.getOperation().getOperation());
                            filterSetDTO.setOperation(operationDTO);
                        }
                        
                        if (filterSet.getFormatItem() != null) {
                            filterSetDTO.setResponseItemid(filterSet.getFormatItem().getId());
                        }
                        
                        return filterSetDTO;
                    })
                    .collect(Collectors.toList());
                
                filterSetListDTO.setFilterSets(filterSetDTOs);
            }
            
            responseDTO.setFilterSetList(filterSetListDTO); 
        }
        
        return responseDTO;
    }
}

