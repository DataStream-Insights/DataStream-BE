package com.wnsud9771.service.pipeline;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@RequiredArgsConstructor
@Slf4j
public class ExecutableSendService {
	private final RestTemplate restTemplate;
	
	public boolean sendkafkaproject(AddPipelineDTO dto) {
		try {
			log.info("보내려는 dto {}",dto.getPipelineId());
	 		restTemplate.postForObject("http://localhost:8083/ids/start", dto, AddPipelineDTO.class);	 		
	 		return true; 
	 	}catch (Exception e){
	 		log.info("sendFormatTopic 토픽 전송실패");
	 		return false;
	 	}
		
	}
	
	public boolean stopprocesskafka(AddPipelineDTO dto) {
		try {
	 		restTemplate.postForObject("http://localhost:8083/ids/stop", dto, AddPipelineDTO.class);	 		
	 		return true; 
	 	}catch (Exception e){
	 		log.info("sendFormatTopic 토픽 전송실패");
	 		return false;
	 	}
	}
}
