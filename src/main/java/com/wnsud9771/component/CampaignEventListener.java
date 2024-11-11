package com.wnsud9771.component;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.event.CampaignCreatedEvent;
import com.wnsud9771.service.sendkafka.CreateTopicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class CampaignEventListener {
	private final CreateTopicService createTopicService;
	
	 @EventListener
	    public void handleCampaignCreated(CampaignCreatedEvent event) {
	        try {
	            
	            CampaignIdDTO dto = new CampaignIdDTO();
	            dto.setCampaingId(event.getCampaignId());
	            
	            createTopicService.sendCampaignTopic(dto);
	            
	            log.info("성공 캠페인아이디 : {}", 
	                event.getCampaignId());
	        } catch (Exception e) {
	            log.error("실패 캠페인 아이디 : {}", event.getCampaignId(), e);
	            // 에러 처리 로직 추가 가능
	        }
	    }
}
