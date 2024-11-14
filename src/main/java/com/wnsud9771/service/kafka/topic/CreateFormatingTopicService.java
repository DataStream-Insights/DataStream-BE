package com.wnsud9771.service.kafka.topic;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.dto.topic.CampainIdFormatIdDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateFormatingTopicService {
	private final RestTemplate restTemplate;
	
	public boolean sendFormatTopic(CampainIdFormatIdDTO campaignIdformatIdDTO) {
	 	log.info("포맷토픽생성  캠페인아이디 data {} : ", campaignIdformatIdDTO.getFormatId());
	 	log.info("포맷토픽생성  포맷아이디 data {} : ", campaignIdformatIdDTO.getFormatId());
	 	try {
	 		restTemplate.postForObject("http://localhost:8084/topics/formating", campaignIdformatIdDTO, CampainIdFormatIdDTO.class);	 		
	 		return true; 
	 	}catch (Exception e){
	 		log.info("sendFormatTopic 토픽 전송실패");
	 		return false;
	 	}
    }
}
