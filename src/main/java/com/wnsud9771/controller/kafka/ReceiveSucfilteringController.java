package com.wnsud9771.controller.kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sendkafka")
@RequiredArgsConstructor
@Slf4j
public class ReceiveSucfilteringController {
	
	@Operation(summary = "카프카 프로젝트에서 실행후 데이터들이 filteringdata에 쌓이면 그때 받는 api", description = "각자 데이터들 테이블로 하기")
	@PostMapping("/getsuc")
	 public ResponseEntity<String> kafkasendbestr(@RequestBody String str) {
		
        return ResponseEntity.ok(str);
    }
}
