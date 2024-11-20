package com.wnsud9771.component.pipeline;

import org.springframework.stereotype.Component;

import com.wnsud9771.event.pipeline.PipelineStartEvent;
import com.wnsud9771.event.pipeline.PipelineStopEvent;
import com.wnsud9771.service.pipeline.ExecutableSendService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class PipelineEventListener {
	private final ExecutableSendService executableSendService;
	
	public void sendidtocreatetopic(PipelineStartEvent event) {
        try {
            
        	if(!executableSendService.sendkafkaproject(event.getDto())) {
        		// 카프카 프로젝트에 dto 보내기 실패시
        		
        		log.info("프로젝트로 토픽생성 dto 못보냄 ");
        	}
        	
        	
            
        } catch (Exception e) {
            log.error("", event.getDto(), e);
            // 에러 처리 로직 추가 가능
        }
    }
	
	public void stopprocess(PipelineStopEvent event) {
		
	}
}
