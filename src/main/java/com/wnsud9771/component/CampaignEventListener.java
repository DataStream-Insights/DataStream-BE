package com.wnsud9771.component;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.event.CampaignCreatedEvent;
import com.wnsud9771.service.sendkafka.CampaignRecordtService;
import com.wnsud9771.service.sendkafka.CreateTopicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Component
@Slf4j
@RequiredArgsConstructor
public class CampaignEventListener {
	private final CreateTopicService createTopicService;
	private final CampaignRecordtService campaignOffSetService;
	
	 @EventListener
	    public void handleCampaignCreated(CampaignCreatedEvent event) {
	        try {
	            
	            CampaignIdDTO dto = new CampaignIdDTO();
	            dto.setCampaingId(event.getCampaignId());
	            
	            //db에 저장된 캠페인 id들 전부 보내거나, 못보낸건 db에 저장
	            campaignOffSetService.findNotSendCampaignTopic(dto);
	            
	        } catch (Exception e) {
	            log.error("캠페인 오프셋 db 오류", event.getCampaignId(), e);
	            // 에러 처리 로직 추가 가능
	        }
	    }
}
