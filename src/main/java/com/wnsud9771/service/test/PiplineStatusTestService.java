package com.wnsud9771.service.test;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.dto.pipeline.add.AddFilterTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddFormatTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class PiplineStatusTestService {
	private final UpdateConsumerService updateConsumerService;
	private final UpdatePipelineStatusService updatePipelineStatusService;

	public void testStatus(AddPipelineDTO receivedto) {

		if (receivedto.getAddcampaignTopic() != null) {
			CampaignIdDTO campaignIddto = new CampaignIdDTO();
			campaignIddto.setCampaingId(receivedto.getAddcampaignTopic().getCampaignId());
			//updateConsumerService.updateCampaignConsumer(pipelineId, campaignId, true, targetTopic);
			updateConsumerService.updateCampaignConsumer(receivedto.getPipelineId(),campaignIddto.getCampaingId(),true,"test");
			// FormatTopic 토픽,컨슈머 생성
			if (receivedto.getAddcampaignTopic().getAddFormatTopics() != null) {
				for (AddFormatTopicDTO addformatTopicdto : receivedto.getAddcampaignTopic().getAddFormatTopics()) {
//				CampaignIdFormatIdDTO campaignIdFormatIdDTO = new CampaignIdFormatIdDTO();
//				campaignIdFormatIdDTO.setCampaignId(campaignIddto.getCampaingId());
//				campaignIdFormatIdDTO.setFormatId(addformatTopicdto.getFormatId());

					// eventPublisher.publishEvent(new FormatCreatedEvent(this,
					// campaignIdFormatIdDTO));
					//updateConsumerService.updateFormatConsumer(pipelineId, campaignId, formatId,true, targetTopic);
					updateConsumerService.updateFormatConsumer(receivedto.getPipelineId(),campaignIddto.getCampaingId(),addformatTopicdto.getFormatId(),true,"test");
					// 필터토픽 ,컨슈머 생성
					if (addformatTopicdto.getAddFilterTopics() != null) {
						for (AddFilterTopicDTO filterTopicdto : addformatTopicdto.getAddFilterTopics()) {

//						CampaignIdFormatIdFilterIdDTO campaignIdFormatIdFilterIdDTO = new CampaignIdFormatIdFilterIdDTO();
//						campaignIdFormatIdFilterIdDTO.setCampaignId(campaignIddto.getCampaingId());
//						campaignIdFormatIdFilterIdDTO.setFormatId(addformatTopicdto.getFormatId());
//						campaignIdFormatIdFilterIdDTO.setFilterId(filterTopicdto.getFilterId());
//						
//						eventPublisher.publishEvent(new FilterCreatedEvent(this, campaignIdFormatIdFilterIdDTO)); //포맷 CampaignIdFormatIdFilterIdDTO
							//updateConsumerService.updateFilterConsumer(pipelineId,formatId, filterId, true, targetTopic);
							updateConsumerService.updateFilterConsumer(receivedto.getPipelineId(),addformatTopicdto.getFormatId(), filterTopicdto.getFilterId(), true, "test");
						}
					}
				}
			}
		}

		// 컨슈머들 모두 true 되면 pipeline status -> true
		log.info("파이프라인 끝나기 이전 상태: {}", receivedto.getPipelineId());
		updatePipelineStatusService.changePipelineStatus(receivedto.getPipelineId());
		log.info("파이프라인 끝나고 난후 상태: {}", receivedto.getPipelineId());
		// 토픽 다생성되면 해당 필터링 토픽에서 데이터 꺼내와서 db에 저장

	}
	
	public void testStatus2(AddPipelineDTO receivedto) {
		updatePipelineStatusService.changePipelineStatus(receivedto.getPipelineId());
	}
}
