package com.wnsud9771.controller.kafka.log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.receivelog.LogDTO;
import com.wnsud9771.service.format.ReceiveLogAndParseTitleService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@Slf4j
public class ReceiveLogController {
	private final ReceiveLogAndParseTitleService receiveLogAndParseTitleService;

	@Operation(summary = "첫 쇼핑몰 로그가 들어오는 경로", description = "맨처음 쇼핑몰에서 spring-kafka로 컨슈밍되어 들어온 로그를 그대로 받아와서 title추출후 db에 title과 로그를 같이 저장")
	@PostMapping("/receive")
	public ResponseEntity<LogDTO> receiveUserFromFirstService(@RequestBody LogDTO logDTO) {
		log.info("log data {}:", logDTO.getLog_data());
		return ResponseEntity.ok(receiveLogAndParseTitleService.receiveLogData(logDTO));
	}
}
