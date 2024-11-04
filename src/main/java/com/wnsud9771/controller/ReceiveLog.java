//package com.wnsud9771.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wnsud9771.dto.log.LogDTO;
//import com.wnsud9771.service.format.FieldNameService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@RestController
//@RequestMapping("/logs")
//@RequiredArgsConstructor
//@Slf4j
//public class ReceiveLog {
//	private final FieldNameService fieldNameService;
//	
//	@PostMapping("/receive")
//    public ResponseEntity<LogDTO> receiveUserFromFirstService(@RequestBody LogDTO logDTO) {
//		log.info("log data {}:", logDTO.getLog_data());
//        return ResponseEntity.ok(fieldNameService.receiveLogData(logDTO));
//    } 
//}
