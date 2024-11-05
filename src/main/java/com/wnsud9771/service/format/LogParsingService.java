package com.wnsud9771.service.format;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wnsud9771.dto.format.parsing.LogItemDTO;
import com.wnsud9771.dto.format.parsing.LogParseDTO;
import com.wnsud9771.reoisitory.format.TitleAndLogRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogParsingService { // jackson 라이브러리 사용

	private final TitleAndLogRepository titleAndLogRepository;

	// 초기 substring 파싱 title, startdepth, enddepth 받아서 로그 파싱
	public List<LogItemDTO> processLogData(LogParseDTO logParseDTO) {
		log.info("DTO.title {}", logParseDTO.getLog());
		log.info("DTO.title {}", logParseDTO.getStartdepth());
		log.info("DTO.title {}", logParseDTO.getEnddepth());

		String logEntry = logParseDTO.getLog();

		List<LogItemDTO> result = new ArrayList<>();

		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(logEntry);
			processNodeWithDepth(rootNode, "", 0, logParseDTO.getStartdepth(), logParseDTO.getEnddepth(), result);
		} catch (Exception e) {
			throw new RuntimeException("로그 데이터 처리 중 오류가 발생했습니다.", e);
		}

		return result;

	}

	// path 기반으로 파싱 
	// 컨트롤러의 path 기반 검색 요청 처리
	public List<LogItemDTO> parseLog(String logEntry, String path) {
		List<LogItemDTO> result = new ArrayList<>();
		try {
			ObjectMapper mapper = new ObjectMapper();
			JsonNode rootNode = mapper.readTree(logEntry);
			JsonNode targetNode = findNodeByPath(rootNode, path);
			if (targetNode != null) {
				processNodeFromPath(targetNode, path, result);
			}
		} catch (Exception e) {
			throw new RuntimeException("로그 파싱 중 오류가 발생했습니다.", e);
		}
		return result;
	}

	// 실제 노드 파싱 로직을 담당하는 private 메소드 key, value, path, hasChild 정보 추출
	private List<LogItemDTO> processNodeFromPath(JsonNode node, String currentPath, List<LogItemDTO> result) {
		if (node.isObject()) {
			Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
			while (fields.hasNext()) {
				Map.Entry<String, JsonNode> field = fields.next();
				String newPath = currentPath.isEmpty() ? field.getKey() : currentPath + "." + field.getKey();
				boolean hasChild = field.getValue().isObject() || field.getValue().isArray();

				result.add(LogItemDTO.builder().key(field.getKey()).value(field.getValue().toString()).path(newPath)
						.hasChild(hasChild).build());
			}
		}
		return result;
	}
	
	//depth 기반 재귀 파싱을 위한 private 메소드
	//지정된 depth 범위의 노드만 처리
	private void processNodeWithDepth(JsonNode node, String currentPath, int currentDepth, int startDepth, int endDepth,
			List<LogItemDTO> result) {
		if (currentDepth > endDepth || !node.isObject())
			return;

		Iterator<Map.Entry<String, JsonNode>> fields = node.fields();
		while (fields.hasNext()) {
			Map.Entry<String, JsonNode> field = fields.next();
			String newPath = currentPath.isEmpty() ? field.getKey() : currentPath + "." + field.getKey();

			if (currentDepth >= startDepth && currentDepth <= endDepth) {
				boolean hasChild = field.getValue().isObject() || field.getValue().isArray();

				result.add(LogItemDTO.builder().key(field.getKey()).value(field.getValue().toString()).path(newPath)
						.hasChild(hasChild).build());
			}

			processNodeWithDepth(field.getValue(), newPath, currentDepth + 1, startDepth, endDepth, result);
		}
	}

	// 주어진 path에 해당하는 노드를 찾는 private 메소드
	// path 기반 검색에 사용
	private JsonNode findNodeByPath(JsonNode node, String path) {
		String[] parts = path.split("\\.");
		JsonNode current = node;

		for (String part : parts) {
			if (current == null || !current.isObject()) {
				return null;
			}
			current = current.get(part);
		}
		return current;
	}

}