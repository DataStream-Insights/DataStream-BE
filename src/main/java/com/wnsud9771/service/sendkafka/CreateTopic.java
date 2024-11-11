package com.wnsud9771.service.sendkafka;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.reoisitory.format.TitleAndLogRepository;
import com.wnsud9771.service.format.LogParsingService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateTopic {
	private final RestTemplate restTemplate;
	private static final String RECEIVE_LOG_URL = "http://localhost:8084/logs/formating";
	
	
}
