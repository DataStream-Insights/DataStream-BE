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
import com.wnsud9771.dto.filter.management.ResponseFilterManagementDTO;
import com.wnsud9771.dto.filter.management.search.FindManagementByIdDTO;
import com.wnsud9771.dto.filter.management.search.ResponseFilterSetListDTO;
import com.wnsud9771.dto.filter.management.search.SearchFilterSetDTO;
import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.FIlterentity.FilterSet;
import com.wnsud9771.entity.FIlterentity.FilterSetList;
import com.wnsud9771.entity.FIlterentity.Filtervalue;
import com.wnsud9771.entity.FIlterentity.Operation;
import com.wnsud9771.entity.Formatentity.FormatManagement;
import com.wnsud9771.entity.item.FormatItem;
import com.wnsud9771.entity.kafka_topic.FormatFilter;
import com.wnsud9771.reoisitory.filter.FilterManagementRepository;
import com.wnsud9771.reoisitory.filter.FilterSetListRepository;
import com.wnsud9771.reoisitory.filter.OperationRepository;
import com.wnsud9771.reoisitory.format.FormatManagementRepository;
import com.wnsud9771.reoisitory.item.FormatItemRepository;
import com.wnsud9771.reoisitory.mapping.FormatFilterRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FilterManagementService {
	private final FilterManagementRepository filterManagementRepository;
	private final FormatItemRepository formatItemRepository;
	private final OperationRepository operationRepository;
	private final FilterSetListRepository filterSetListRepository;
	private final FormatFilterRepository formatFilterRepository;
	private final  FormatManagementRepository  formatManagementRepository;
	// 필터 관리 생성
	public ResponseFilterManagementDTO createFilterManagement(ResponseFilterManagementDTO dto, String formatID) {
		FilterManagement filterManagement = new FilterManagement();

		filterManagement.setFilterName(dto.getFiltername());
		filterManagement.setFilterManageId(dto.getFiltermanage_id());

		if (dto.getFilterSetList() != null) {
			FilterSetList filterSetList = new FilterSetList();
			filterSetList.setFilterManagement(filterManagement); // 양방향 관계 설정
			filterManagement.setFilterSetList(filterSetList);

			if (!dto.getFilterSetList().getFilterSets().isEmpty()) {
				List<FilterSet> filterSets = new ArrayList<>();

				dto.getFilterSetList().getFilterSets().forEach(filterSetDTO -> {
					FilterSet filterSet = new FilterSet();
					filterSet.setFilterSetList(filterSetList); // 양방향 관계 설정

					// filterset의 andor 설정
					filterSet.setAndor(filterSetDTO.getAndor());

					// filtervalue entity에 dto 넣어서 통으로 set
					if (filterSetDTO.getFiltervalue() != null) {
						Filtervalue filtervalue = new Filtervalue();
						filtervalue.setValue(filterSetDTO.getFiltervalue().getValue());
						filterSet.setFiltervalue(filtervalue);
						filtervalue.getFilterSets().add(filterSet); // 양방향 관계 설정
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
								.orElseThrow(() -> new RuntimeException(
										"Operation not found: " + filterSetDTO.getOperation().getOperation()));
						filterSet.setOperation(operation);
					}

					// formatitem entity에 dto 넣어서 통으로 set
					if (filterSetDTO.getResponseItemid() != null) {
						FormatItem formatItem = formatItemRepository.getReferenceById(filterSetDTO.getResponseItemid());
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

		// formatmanagement랑 연결 저장 하는 부분
		
		
		FormatManagement entity = formatManagementRepository.findByFormatID(formatID).orElseThrow(() -> new EntityNotFoundException("캠페인 아이디를 찾을 수 없음 :  " + formatID));
		
		
		FormatFilter formatFilter = new FormatFilter();
		formatFilter.setFilterManagement(savedFilterManagement);
		formatFilter.setFormatManagement(entity);
		formatFilterRepository.save(formatFilter);
        
		return convertToResponseDTO(savedFilterManagement);
	}
	
	public ResponseFilterManagementDTO createonlyFilterManagement(ResponseFilterManagementDTO dto) {
		FilterManagement filterManagement = new FilterManagement();

		filterManagement.setFilterName(dto.getFiltername());
		filterManagement.setFilterManageId(dto.getFiltermanage_id());

		if (dto.getFilterSetList() != null) {
			FilterSetList filterSetList = new FilterSetList();
			filterSetList.setFilterManagement(filterManagement); // 양방향 관계 설정
			filterManagement.setFilterSetList(filterSetList);

			if (!dto.getFilterSetList().getFilterSets().isEmpty()) {
				List<FilterSet> filterSets = new ArrayList<>();

				dto.getFilterSetList().getFilterSets().forEach(filterSetDTO -> {
					FilterSet filterSet = new FilterSet();
					filterSet.setFilterSetList(filterSetList); // 양방향 관계 설정

					// filterset의 andor 설정
					filterSet.setAndor(filterSetDTO.getAndor());

					// filtervalue entity에 dto 넣어서 통으로 set
					if (filterSetDTO.getFiltervalue() != null) {
						Filtervalue filtervalue = new Filtervalue();
						filtervalue.setValue(filterSetDTO.getFiltervalue().getValue());
						filterSet.setFiltervalue(filtervalue);
						filtervalue.getFilterSets().add(filterSet); // 양방향 관계 설정
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
								.orElseThrow(() -> new RuntimeException(
										"Operation not found: " + filterSetDTO.getOperation().getOperation()));
						filterSet.setOperation(operation);
					}

					// formatitem entity에 dto 넣어서 통으로 set
					if (filterSetDTO.getResponseItemid() != null) {
						FormatItem formatItem = formatItemRepository.getReferenceById(filterSetDTO.getResponseItemid());
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

//		// formatmanagement랑 연결 저장 하는 부분
//		
//		
//		FormatManagement entity = formatManagementRepository.findByFormatID(formatID).orElseThrow(() -> new EntityNotFoundException("캠페인 아이디를 찾을 수 없음 :  " + formatID));
//		
//		
//		FormatFilter formatFilter = new FormatFilter();
//		formatFilter.setFilterManagement(savedFilterManagement);
//		formatFilter.setFormatManagement(entity);
//		formatFilterRepository.save(formatFilter);
        
		return convertToResponseDTO(savedFilterManagement);
	}
	
	// only 필터 관리 모두 검색
		public List<FilterManagementDTO> findonlyManagements() {

			// id 받아서 id로 검색후 해당 리스트들만 뽑는
			//List<FormatFilter> formatFilters = formatFilterRepository.findByFormatManagement_FormatID(formatID);

//			List<Long> filterManagementIds = formatFilters.stream()
//					.map(formatFilter -> formatFilter.getFilterManagement().getId()).collect(Collectors.toList());
			
			List<FilterManagement> filtermanages =filterManagementRepository.findAll();

			return filtermanages.stream().map(this::managementconvertToDTO).collect(Collectors.toList());

		}
	

	//포맷연관 필터 관리 모두 검색
	public List<FilterManagementDTO> findManagements(String formatID) {

		// id 받아서 id로 검색후 해당 리스트들만 뽑는
		List<FormatFilter> formatFilters = formatFilterRepository.findByFormatManagement_FormatID(formatID);

		List<Long> filterManagementIds = formatFilters.stream()
				.map(formatFilter -> formatFilter.getFilterManagement().getId()).collect(Collectors.toList());

		return filterManagementRepository.findAllById(filterManagementIds).stream().map(this::managementconvertToDTO)
				.collect(Collectors.toList());
		
	}

	// 필터 관리 엔티티 -> dto
	private FilterManagementDTO managementconvertToDTO(FilterManagement entity) {
		FilterManagementDTO dto = new FilterManagementDTO();
		dto.setId(entity.getId());
		dto.setFilterName(entity.getFilterName());
		dto.setFilterManageId(entity.getFilterManageId());
		return dto;
	}

	// 필터 관리 상세조회 id로 검색
	public FindManagementByIdDTO findById(Long id) {
		FindManagementByIdDTO responseDto = new FindManagementByIdDTO();

		FilterSetList filterSetList = filterSetListRepository.findByFilterManagementId(id)
				.orElseThrow(() -> new RuntimeException("###management에서 filterSetList id 잘못 조회"));

		ResponseFilterSetListDTO responsefilterSetListDTO = new ResponseFilterSetListDTO();

		responsefilterSetListDTO.setSearchFilterSetDTOs(filterSetList.getFilterSets().stream().map(filterSet -> {

			FilterSet filterSets = new FilterSet();
			filterSets = filterSet;

			SearchFilterSetDTO dto = new SearchFilterSetDTO();

			dto.setAndor(filterSets.getAndor());
			dto.setItem_alias(filterSets.getFormatItem().getItemAlias());

			OperationDTO operationDTO = new OperationDTO();
			operationDTO.setOperation(filterSets.getOperation().getOperation());
			dto.setOperation(operationDTO);

			FiltervalueDTO filtervalueDTO = new FiltervalueDTO();
			filtervalueDTO.setValue(filterSets.getFiltervalue().getValue());
			dto.setFiltervalue(filtervalueDTO);

			return dto;
		}).collect(Collectors.toList()));

		responseDto.setResponseFilterSetListDTO(responsefilterSetListDTO);

		return responseDto;
	}

	// entity 다시 응답 dto로 변환
	private ResponseFilterManagementDTO convertToResponseDTO(FilterManagement filterManagement) {
		ResponseFilterManagementDTO responseDTO = new ResponseFilterManagementDTO();

		responseDTO.setFiltername(filterManagement.getFilterName());
		responseDTO.setFiltermanage_id(filterManagement.getFilterManageId());

		if (filterManagement.getFilterSetList() != null) {
			FilterSetListDTO filterSetListDTO = new FilterSetListDTO();

			if (filterManagement.getFilterSetList().getFilterSets() != null
					&& !filterManagement.getFilterSetList().getFilterSets().isEmpty()) {
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
						}).collect(Collectors.toList());

				filterSetListDTO.setFilterSets(filterSetDTOs);
			}

			responseDTO.setFilterSetList(filterSetListDTO);
		}

		return responseDTO;
	}
}
