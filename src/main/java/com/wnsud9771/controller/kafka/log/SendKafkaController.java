package com.wnsud9771.controller.kafka.log;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.receivelog.SendLogDTO;
import com.wnsud9771.service.sendkafka.FormatingSendService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sendkafka")
@RequiredArgsConstructor
@Slf4j
public class SendKafkaController {
	private final FormatingSendService formatingSendService;

	@Operation(summary = "포맷 필드 파싱 후 springkafka로 전송", description = "포맷 필드로 파싱 하는거 추가 원래의 로그 제목 말고, 포맷제목 앞에 붙여서 json으로 파싱 파싱한 포맷팅 로그 다시 spring-kafka( \"/formating\")으로 전송")
	@PostMapping("/sendformating") // 필드 설정 정보 저장하는 api
	 public ResponseEntity<SendLogDTO> sendFormatingLogToKafkaService(@RequestBody SendLogDTO sendlogDto) {
        return ResponseEntity.ok(formatingSendService.sendLogData(sendlogDto));
    }
	
}
