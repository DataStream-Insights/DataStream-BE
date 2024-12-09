//package com.wnsud9771.component;
//
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import com.wnsud9771.dto.campaign.CampaignIdDTO;
//import com.wnsud9771.dto.format.FormatIdDTO;
//import com.wnsud9771.event.FormatCreatedEvent;
//import com.wnsud9771.service.kafka.record.FormatRecordService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class FormatEventListener {
//	private final FormatRecordService formatRecordService;
//	
//	 @EventListener
//	    public void handleFormatCreated(FormatCreatedEvent event) {
//	        try {
//	            
//	            FormatIdDTO formatIdDTO = new FormatIdDTO();
//	            formatIdDTO.setFormatId(event.getFormatId());
//	            
//	            CampaignIdDTO campaignIdDTO = new CampaignIdDTO();
//	            campaignIdDTO.setCampaingId(event.getCampaignId());
//	            
//	            //db에 저장된 포맷 id들 전부 보내거나, 못보낸건 db에 저장
//	            formatRecordService.findNotSendFormatTopic(formatIdDTO,campaignIdDTO);
//	            
//	        } catch (Exception e) {
//	            log.error("캠페인 오프셋 db 오류", event.getFormatId(), e);
//	            // 에러 처리 로직 추가 가능
//	        }
//	    }
//}
