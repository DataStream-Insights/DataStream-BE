package com.wnsud9771.controller.receivelog;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.receivelog.LogDTO;
import com.wnsud9771.service.format.ReceiveLogAndParseTitleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/logs")
@RequiredArgsConstructor
@Slf4j
public class ReceiveLogController {
	private final ReceiveLogAndParseTitleService fieldNameService;
	
	@PostMapping("/receive")
    public ResponseEntity<LogDTO> receiveUserFromFirstService(@RequestBody LogDTO logDTO) {
		log.info("log data {}:", logDTO.getLog_data());
        return ResponseEntity.ok(fieldNameService.receiveLogData(logDTO));
    } 
}
