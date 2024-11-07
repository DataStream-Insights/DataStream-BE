package com.wnsud9771.service.sendkafka;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.dto.receivelog.LogDTO;
import com.wnsud9771.dto.receivelog.SendLogDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormatingSendService {

	 private final RestTemplate restTemplate;
	 private static final String RECEIVE_LOG_URL = "http://localhost:8080/logs/formating"; //kafka2로 전송
	 
	 public SendLogDTO sendLogData(SendLogDTO SendlogDTO) {
		 	log.info("send dto data {} : ", SendlogDTO.getLog_data());
	        return restTemplate.postForObject(RECEIVE_LOG_URL, SendlogDTO, SendLogDTO.class);
	 }
}
