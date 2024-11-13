package com.wnsud9771.service.kafka.topic;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.dto.receivelog.LogDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreateCampaignTopicService {
	private final RestTemplate restTemplate;
	//private static final String RECEIVE_LOG_URL = "http://localhost:8084/topics/campaign";
	
	public boolean sendCampaignTopic(CampaignIdDTO CampaignIdDTO) {
	 	log.info("send dto data {} : ", CampaignIdDTO.getCampaingId());
	 	try {
	 		restTemplate.postForObject("http://localhost:8083/topics/campaign", CampaignIdDTO, CampaignIdDTO.class);	 		
	 		return true; 
	 	}catch (Exception e){
	 		log.info("sendCampaignTopic 토픽 전송실패");
	 		return false;
	 	}
    }
	
}
