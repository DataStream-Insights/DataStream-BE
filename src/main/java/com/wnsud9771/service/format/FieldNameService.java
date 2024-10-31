package com.wnsud9771.service.format;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wnsud9771.dto.format.FieldNameDTO;
import com.wnsud9771.entity.Formatentity.FieldName;
import com.wnsud9771.reoisitory.format.FieldNameRepository;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FieldNameService {
	private String[] logData = {
			"{\"title\":\"사용자 A의 전자제품 조회 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:23:45+09:00\",\"visitor_id\":\"2cff4a12e87f499b\",\"url\":\"https://example.com/products/category/electronics\",\"event_action\":\"View\",\"user_id\":\"user_123456\"}}",
			"{\"title\":\"사용자 B의 의류 조회 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:25:12+09:00\",\"visitor_id\":\"3dff5b23f98g500c\",\"url\":\"https://example.com/products/category/clothing\",\"event_action\":\"View\",\"user_id\":\"user_123457\"}}",
			"{\"title\":\"사용자 C의 장바구니 추가 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:28:33+09:00\",\"visitor_id\":\"4eff6c34g09h501d\",\"url\":\"https://example.com/products/category/electronics\",\"event_action\":\"Add_to_Cart\",\"user_id\":\"user_123458\"}}",
			"{\"title\":\"사용자 D의 구매 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:30:15+09:00\",\"visitor_id\":\"5fgg7d45h10i502e\",\"url\":\"https://example.com/products/category/books\",\"event_action\":\"Purchase\",\"user_id\":\"user_123459\"}}",
			"{\"title\":\"사용자 E의 상품 클릭 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:32:48+09:00\",\"visitor_id\":\"6ghh8e56i11j503f\",\"url\":\"https://example.com/products/category/sports\",\"event_action\":\"Click\",\"user_id\":\"user_123460\"}}" };

	private final FieldNameRepository fieldNameRepository;

	@PostConstruct //임시로 로그데이터 바로 넣어버리기
	public List<FieldNameDTO> processLogData() {
		if (!fieldNameRepository.findAll().isEmpty()) { //임시로 위에 로그 데이터 있으면 db에 또 안들어가게 막기
            return new ArrayList<>();
        }
		List<FieldNameDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();

		try {
			for (String logEntry : logData) {
				JsonNode rootNode = mapper.readTree(logEntry);
				String title = rootNode.get("title").asText();
				JsonNode contentsNode = rootNode.get("contents");

				// contents의 각 필드를 개별 DTO로 변환
				Iterator<Map.Entry<String, JsonNode>> fields = contentsNode.fields();
				while (fields.hasNext()) {
					Map.Entry<String, JsonNode> field = fields.next();
					FieldNameDTO dto = new FieldNameDTO();
					dto.setTitle(title);
					dto.setField_name(field.getKey());
					dto.setItem_contents_ex(field.getValue().asText());

					// Entity로 변환하여 저장 (필요한 경우)

					FieldName entity = convertToEntity(dto);
					fieldNameRepository.save(entity);

					result.add(dto);
				}
			}
		} catch (Exception e) {
			throw new RuntimeException("로그 데이터 처리 중 오류가 발생했습니다.", e);
		}

		return result;
	}

	// DTO -> Entity 변환
	private FieldName convertToEntity(FieldNameDTO dto) {
		FieldName entity = new FieldName();
		entity.setField_name(dto.getField_name());
		entity.setItem_contents_ex(dto.getItem_contents_ex());
		entity.setTitle(dto.getTitle());
		return entity;
	}
	
//	// Entity -> DTO 변환
//	private FieldNameDTO convertToDTO(FieldName entity) {
//		FieldNameDTO dto = new FieldNameDTO();
//		dto.setField_name(entity.getField_name());
//		dto.setItem_contents_ex(entity.getItem_contents_ex());
//		dto.setTitle(entity.getTitle());
//		return dto;
//	}

	// 제목 목록만 가져오기
//	public List<String> getAllLogTitles() {
//		return fieldNameRepository.findDistinctTitle(); // 또는 적절한 쿼리 메서드
//	}
//
//	// 특정 제목에 해당하는 로그 데이터 가져오기
//	public List<FieldNameDTO> getLogsByTitle(String title) {
//		List<FieldName> entities = fieldNameRepository.findByTitle(title);
//		return entities.stream().map(this::convertToDTO).collect(Collectors.toList());
//	}

	
	
	// log title 선택한거 받아서 field_name이랑 contents 보여주기
	// 

}
