//package com.wnsud9771.controller.kafka.topic;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wnsud9771.controller.kafka.ReceiveLogController;
//import com.wnsud9771.dto.receivelog.LogDTO;
//import com.wnsud9771.service.format.ReceiveLogAndParseTitleService;
//
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping("/topics")
//@RequiredArgsConstructor
//@Slf4j
//public class SendTopicController {
//	
//	@Operation(summary = "캠페인이 생성되고 캠페인 토픽 이름 보내는 api", description = "맨처음 쇼핑몰에서 spring-kafka로 컨슈밍되어 들어온 로그를 그대로 받아와서 title추출후 db에 title과 로그를 같이 저장")
//	@PostMapping("/campaign")
//	public ResponseEntity<LogDTO> receiveUserFromFirst(@RequestBody LogDTO logDTO) {
//		log.info("log data {}:", logDTO.getLog_data());
//		return ResponseEntity.ok(receiveLogAndParseTitleService.receiveLogData(logDTO));
//	}
//	
//	@Operation(summary = "포맷이 생성되고 포맷 토픽 이름 보내는 api", description = "맨처음 쇼핑몰에서 spring-kafka로 컨슈밍되어 들어온 로그를 그대로 받아와서 title추출후 db에 title과 로그를 같이 저장")
//	@PostMapping("/format")
//	public ResponseEntity<LogDTO> receiveUserFromFirst(@RequestBody LogDTO logDTO) {
//		log.info("log data {}:", logDTO.getLog_data());
//		return ResponseEntity.ok(receiveLogAndParseTitleService.receiveLogData(logDTO));
//	}
//	
//	@Operation(summary = "", description = "맨처음 쇼핑몰에서 spring-kafka로 컨슈밍되어 들어온 로그를 그대로 받아와서 title추출후 db에 title과 로그를 같이 저장")
//	@PostMapping("/filter")
//	public ResponseEntity<LogDTO> receiveUserFromFirst(@RequestBody LogDTO logDTO) {
//		log.info("log data {}:", logDTO.getLog_data());
//		return ResponseEntity.ok(receiveLogAndParseTitleService.receiveLogData(logDTO));
//	}
//}
