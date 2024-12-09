//package com.wnsud9771.component;
//
//import org.springframework.context.event.EventListener;
//import org.springframework.stereotype.Component;
//
//import com.wnsud9771.dto.campaign.CampaignIdDTO;
//import com.wnsud9771.dto.filter.FilterIdDTO;
//import com.wnsud9771.dto.format.FormatIdDTO;
//import com.wnsud9771.event.FilterCreatedEvent;
//import com.wnsud9771.service.kafka.record.FilterRecordService;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
//@Component
//@Slf4j
//@RequiredArgsConstructor
//public class FilterEventListner {
//	private final FilterRecordService filterRecordService;
//	
//	 @EventListener
//	    public void handleFormatCreated(FilterCreatedEvent event) {
//	        try {
//	            
//	        	FilterIdDTO  filterIdDTO  = new FilterIdDTO ();
//	            filterIdDTO.setFilterId(event.getFilterId());
//	            
//	            FormatIdDTO formatIdDTO = new FormatIdDTO();
//	            formatIdDTO.setFormatId(event.getFormatId());
//	           
//	            
//	            //db에 저장된 포맷 id들 전부 보내거나, 못보낸건 db에 저장
//	            filterRecordService.findNotSendFormatTopic(filterIdDTO,formatIdDTO,event.getCampaignId());
//	            
//	        } catch (Exception e) {
//	            log.error("캠페인 오프셋 db 오류", event.getFormatId(), e);
//	            // 에러 처리 로직 추가 가능
//	        }
//	    }
//}
