package com.wnsud9771.service.kafka.topic;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.dto.topic.CampaignIdFormatIdFilterIdDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateFilterTopicService {
private final RestTemplate restTemplate;
	
	public boolean sendFilterTopic(CampaignIdFormatIdFilterIdDTO dto) {
	 	log.info("필터토픽생성  캠페인아이디 data {} : ", dto.getCampaignId());
	 	log.info("필터토픽생성  포맷아이디 data {} : ", dto.getFormatId());
	 	log.info("필터토픽생성 필터아이디 {} : ", dto.getFilterId());
	 	
	 	try {
	 		restTemplate.postForObject("http://localhost:8085/topics/filtering", dto, CampaignIdFormatIdFilterIdDTO.class);	 		
	 		return true; 
	 	}catch (Exception e){
	 		log.info("sendFilterTopic 토픽 전송실패");
	 		return false;
	 	}
    }
}
