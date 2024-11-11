package com.wnsud9771.service.sendkafka;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.entity.offsetentity.CampaignOffset;
import com.wnsud9771.reoisitory.kafka.CampaignOffsetRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignOffSetService {
	private final CampaignOffsetRepository campaignOffsetRepository;
	private final CreateTopicService createTopicService;
	
	public void findNotSendCampaignTopic(CampaignIdDTO campaignIddto) {
		
		CampaignOffset entity = new CampaignOffset();
		entity = ConverToEntity(campaignIddto);
		
		campaignOffsetRepository.save(entity);
		
		List<CampaignOffset> campaignOffsets =campaignOffsetRepository.findAll();
		
		for (CampaignOffset campaignOffset : campaignOffsets) {
			try {
				CampaignIdDTO dto = new CampaignIdDTO();
				dto.setCampaingId(campaignOffset.getCampaignId());
				if(!createTopicService.sendCampaignTopic(dto)) {
					break;
				}
				campaignOffsetRepository.delete(campaignOffset); // 전송되면 삭제
			}catch(Exception e) {
				log.error("캠페인 토픽 전송 실패, CampaignOffset db에 campaignId 적재중");
			}
			
        }
		
		List<CampaignOffset> NowcampaignOffsets =campaignOffsetRepository.findAll();
		for (CampaignOffset nowcampaignOffset : NowcampaignOffsets) {
			CampaignIdDTO dto = new CampaignIdDTO();
			dto.setCampaingId(nowcampaignOffset.getCampaignId());
			log.info("전송 안된 캠페인 id(토픽) 리스트:{}",dto);
		}
		
	}
	
	private CampaignOffset ConverToEntity(CampaignIdDTO dto) {
		CampaignOffset entity = new CampaignOffset();
		
		entity.setCampaignId(dto.getCampaingId());
		
		return entity;
		
	}
}
