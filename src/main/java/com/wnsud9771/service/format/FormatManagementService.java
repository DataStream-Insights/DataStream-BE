package com.wnsud9771.service.format;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.format.management.FormatItemResponseDTO;
import com.wnsud9771.dto.format.management.FormatManagementResponseDTO;
import com.wnsud9771.dto.format.management.FormatSetResponseDTO;
import com.wnsud9771.dto.format.management.ListFormatManagementDTO;
import com.wnsud9771.entity.Formatentity.FormatManagement;
import com.wnsud9771.entity.Formatentity.FormatSet;
import com.wnsud9771.entity.item.FormatItem;
import com.wnsud9771.reoisitory.format.FormatManagementRepository;
import com.wnsud9771.reoisitory.format.FormatSetRepository;
import com.wnsud9771.reoisitory.item.FormatItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FormatManagementService {
	private final FormatManagementRepository formatManagementRepository;
	private final FormatItemRepository formatItemRepository;
	private final FormatSetRepository formatSetRepository;

	// 컨트롤러 호출 (포맷 생성 )
	public FormatManagementResponseDTO createFormatManagement(FormatManagementResponseDTO dto) {

		FormatManagement formatManagement = new FormatManagement();

		formatManagement.setStart(dto.getStart());
		formatManagement.setEnd(dto.getEnd());
		formatManagement.setFormatname(dto.getFormatname());
		formatManagement.setFormatID(dto.getFormatID());
		formatManagement.setFormatexplain(dto.getFormatexplain());

		// FormatItem들을 먼저 레포에 저장
		if (dto.getFormatSets() != null) {
			dto.getFormatSets().forEach(formatSetDTO -> {
				if (formatSetDTO.getFormatItemResponse() != null) {
					FormatItem formatItem = new FormatItem();
					FormatItemResponseDTO itemDTO = formatSetDTO.getFormatItemResponse();

					formatItem.setFieldName(itemDTO.getFieldName());
					formatItem.setItemAlias(itemDTO.getItemAlias());
					formatItem.setItemExplain(itemDTO.getItemExplain());
					formatItem.setItemType(itemDTO.getItemType());
					formatItem.setItemContent(itemDTO.getItemContent());
					formatItem.setPath(itemDTO.getPath());

					formatItem = formatItemRepository.save(formatItem);

					FormatSet formatSet = new FormatSet();
					formatSet.setFormatItem(formatItem);
					formatManagement.addFormatSet(formatSet); // 하나씩 추가
				}
			});
		}

		// FormatManagement와 FormatSet 저장
		FormatManagement savedFormatManagement = formatManagementRepository.save(formatManagement);

		return convertToDTO(savedFormatManagement);
	}

	// 받아온 포맷 필드+ 포맷관리화면 dto-> entity로 전환하는 함수
	private FormatManagement convertToEntity(FormatManagementResponseDTO dto) {
		FormatManagement formatManagement = new FormatManagement();
		formatManagement.setStart(dto.getStart());
		formatManagement.setEnd(dto.getEnd());
		formatManagement.setFormatname(dto.getFormatname());
		formatManagement.setFormatID(dto.getFormatID());
		formatManagement.setFormatexplain(dto.getFormatexplain());

		if (dto.getFormatSets() != null) {
			dto.getFormatSets().forEach(formatSetDTO -> {
				FormatSet formatSet = new FormatSet();

				// FormatItem 처리
				if (formatSetDTO.getFormatItemResponse() != null) {
					FormatItem formatItem = new FormatItem();
					FormatItemResponseDTO itemDTO = formatSetDTO.getFormatItemResponse();

					// FormatItem 정보 설정
					formatItem.setFieldName(itemDTO.getFieldName());
					formatItem.setItemAlias(itemDTO.getItemAlias());
					formatItem.setItemExplain(itemDTO.getItemExplain());
					formatItem.setItemType(itemDTO.getItemType());
					formatItem.setItemContent(itemDTO.getItemContent());
					formatItem.setPath(itemDTO.getPath());

					formatSet.setFormatItem(formatItem);
				}

				// addFormatSet 메서드를 통해 양방향 연관관계 설정
				formatManagement.addFormatSet(formatSet);
			});
		}

		return formatManagement;
	}

	private FormatManagementResponseDTO convertToDTO(FormatManagement entity) {
		FormatManagementResponseDTO dto = new FormatManagementResponseDTO();

		// 기본 정보 설정
		dto.setStart(entity.getStart());
		dto.setEnd(entity.getEnd());
		dto.setFormatname(entity.getFormatname());
		dto.setFormatID(entity.getFormatID());
		dto.setFormatexplain(entity.getFormatexplain());

		// FormatSet과 FormatItem 변환
		if (entity.getFormatSets() != null) {
			List<FormatSetResponseDTO> formatSetDTOs = entity.getFormatSets().stream().map(formatSet -> {
				FormatSetResponseDTO formatSetDTO = new FormatSetResponseDTO();

				// FormatItem 정보 변환
				if (formatSet.getFormatItem() != null) {
					FormatItemResponseDTO formatItemDTO = new FormatItemResponseDTO();
					FormatItem item = formatSet.getFormatItem();

					formatItemDTO.setFieldName(item.getFieldName());
					formatItemDTO.setItemAlias(item.getItemAlias());
					formatItemDTO.setItemExplain(item.getItemExplain());
					formatItemDTO.setItemType(item.getItemType());
					formatItemDTO.setItemContent(item.getItemContent());
					formatItemDTO.setPath(item.getPath());
					formatSetDTO.setFormatItemResponse(formatItemDTO);
				}

				return formatSetDTO;
			}).collect(Collectors.toList());

			dto.setFormatSets(formatSetDTOs);
		}

		return dto;
	}

	// 전부 검색
	public List<ListFormatManagementDTO> findAllManagement() {
		return formatManagementRepository.findAll().stream().map(this::managementconvertToDTO)
				.collect(Collectors.toList());
	}

	// dto형식으로 매핑
	private ListFormatManagementDTO managementconvertToDTO(FormatManagement entity) {
		ListFormatManagementDTO dto = new ListFormatManagementDTO();
		dto.setId(entity.getId());
		dto.setFormatName(entity.getFormatname());
		dto.setFormatId(entity.getFormatID());
		dto.setFormatexplain(entity.getFormatexplain());
		return dto;
	}

	// management id 검색
	public FormatManagementResponseDTO findById(Long id) {
		FormatManagement formatManagement = formatManagementRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Format Management not found with id: " + id));

		return convertToDTO(formatManagement);
	}
}
