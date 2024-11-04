package com.wnsud9771.service.format;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wnsud9771.dto.format.FieldNameDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LogParsingService {

	public List<FieldNameDTO> processLogData(String logEntry) {
		log.info("processLogData {}", logEntry);
//		if (!fieldNameRepository.findAll().isEmpty()) { //임시로 위에 로그 데이터 있으면 db에 또 안들어가게 막기
//            return new ArrayList<>();
//        }
		List<FieldNameDTO> result = new ArrayList<>();
		ObjectMapper mapper = new ObjectMapper();

		try {
//			for (String logEntry : logData) {
			JsonNode rootNode = mapper.readTree(logEntry);
			String title = rootNode.get("title").asText();
			JsonNode contentsNode = rootNode.get("contents");

			// contents의 각 필드를 개별 DTO로 변환
			Iterator<Map.Entry<String, JsonNode>> fields = contentsNode.fields();
			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> field = fields.next();
				FieldNameDTO dto = new FieldNameDTO();
				dto.setTitle(title);
				dto.setFieldname(field.getKey());
				dto.setItemcontentsex(field.getValue().asText());

				// Entity로 변환하여 저장 (필요한 경우)

//				 FieldName entity = convertToEntity(dto);
//				 fieldNameRepository.save(entity);

				result.add(dto);
//				}
			}
		} catch (Exception e) {
			throw new RuntimeException("로그 데이터 처리 중 오류가 발생했습니다.", e);
		}

		return result;
	}

}
