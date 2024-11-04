package com.wnsud9771.service.format;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wnsud9771.dto.receivelog.LogDTO;
import com.wnsud9771.dto.receivelog.TitleAndLogDTO;
import com.wnsud9771.entity.Formatentity.TitleAndLog;
import com.wnsud9771.reoisitory.format.TitleAndLogRepository;
import com.wnsud9771.reoisitory.item.FormatItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReceiveLogAndParseTitleService { //컨슈밍으로 받아온 로그 title만 뽑아내고 db에 저장
//	private String[] logData = {
//			"{\"title\":\"사용자 A의 전자제품 조회 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:23:45+09:00\",\"visitor_id\":\"2cff4a12e87f499b\",\"url\":\"https://example.com/products/category/electronics\",\"event_action\":\"View\",\"user_id\":\"user_123456\"}}",
//			"{\"title\":\"사용자 B의 의류 조회 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:25:12+09:00\",\"visitor_id\":\"3dff5b23f98g500c\",\"url\":\"https://example.com/products/category/clothing\",\"event_action\":\"View\",\"user_id\":\"user_123457\"}}",
//			"{\"title\":\"사용자 C의 장바구니 추가 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:28:33+09:00\",\"visitor_id\":\"4eff6c34g09h501d\",\"url\":\"https://example.com/products/category/electronics\",\"event_action\":\"Add_to_Cart\",\"user_id\":\"user_123458\"}}",
//			"{\"title\":\"사용자 D의 구매 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:30:15+09:00\",\"visitor_id\":\"5fgg7d45h10i502e\",\"url\":\"https://example.com/products/category/books\",\"event_action\":\"Purchase\",\"user_id\":\"user_123459\"}}",
//			"{\"title\":\"사용자 E의 상품 클릭 로그\",\"contents\":{\"timestamp\":\"2024-10-31T14:32:48+09:00\",\"visitor_id\":\"6ghh8e56i11j503f\",\"url\":\"https://example.com/products/category/sports\",\"event_action\":\"Click\",\"user_id\":\"user_123460\"}}" };
//	private List<String> logData = new ArrayList<>();

	private final FormatItemRepository formatItemRepository;
	private final TitleAndLogRepository titleAndLogRepository;

	public LogDTO receiveLogData(LogDTO logDTO) {
		// System.out.println("Received user data: " + logDTO);
		if (!isDuplicateLog(logDTO.getLog_data())) {
			logDTO.setLog_data(logDTO.getLog_data());
			log.info("List logData {} : ", logDTO.getLog_data());
			//processLogData(logDTO.getLog_data());
		} else {
			log.info("****************same log in DB*************");
		}
		return logDTO;
		// logData.add(logDTO.getLog_data());
	}

	private boolean isDuplicateLog(String logentry) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String logdata = logentry;
			JsonNode rootNode = mapper.readTree(logentry);
			String title = rootNode.get("title").asText();

			// title과 field_name이 동일한 데이터가 이미 존재하는지 확인
			if (formatItemRepository.existsByTitleAndLogData(title, logdata)) {
				log.info("Duplicate entry found - Title: {}, Field: {}, Value: {}", title, logdata);
				return true;
			}
			else {
				TitleAndLogDTO dto = new TitleAndLogDTO();
				dto.setTitle(title);
				dto.setLogdata(logdata);
				 TitleAndLog entity = convertToEntity(dto);
				 titleAndLogRepository.save(entity);
				 
				return false;
			}
			
		} catch (Exception e) {
			log.info("중복은 아니지만 isDuplicateLog() error  :  {}", e.getMessage());
			return false;
		}
	}

	// DTO -> Entity 변환
	private TitleAndLog convertToEntity(TitleAndLogDTO dto) {
		TitleAndLog entity = new TitleAndLog();
		entity.setTitle(dto.getTitle());
		entity.setLogdata(dto.getLogdata());
		
		return entity;
	}
	
	//
//
//	// Entity -> DTO 변환
//	private FieldNameDTO convertToDTO(FieldName entity) {
//		FieldNameDTO dto = new FieldNameDTO();
//		
//		dto.setField_name(entity.getField_name());
//		dto.setItem_contents_ex(entity.getItem_contents_ex());
//		dto.setTitle(entity.getTitle());
//		return dto;
//	}

//	// 제목 목록+ 서브 스트링 가져오기
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
