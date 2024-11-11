package com.wnsud9771.service.sendkafka;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.entity.recordentity.CampaignRecord;
import com.wnsud9771.reoisitory.kafka.CampaignRecordRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CampaignRecordtService {
	private final CampaignRecordRepository campaignRecordRepository;
	private final CreateTopicService createTopicService;
	
	public void findNotSendCampaignTopic(CampaignIdDTO campaignIddto) {
		
		CampaignRecord entity = new CampaignRecord();
		entity = ConverToEntity(campaignIddto);
		
		campaignRecordRepository.save(entity);
		
		List<CampaignRecord> campaignRecords =campaignRecordRepository.findAll();
		
		for (CampaignRecord campaignRecord : campaignRecords) {
			try {
				CampaignIdDTO dto = new CampaignIdDTO();
				dto.setCampaingId(campaignRecord.getCampaignId());
				if(!createTopicService.sendCampaignTopic(dto)) {
					break;
				}
				campaignRecordRepository.delete(campaignRecord); // 전송되면 삭제
			}catch(Exception e) {
				log.error("캠페인 토픽 전송 실패, CampaignOffset db에 campaignId 적재중");
			}
			
        }
		
		List<CampaignRecord> NowcampaignRecords =campaignRecordRepository.findAll();
		for (CampaignRecord nowcampaignRecord : NowcampaignRecords) {
			CampaignIdDTO dto = new CampaignIdDTO();
			dto.setCampaingId(nowcampaignRecord.getCampaignId());
			log.info("전송 안된 캠페인 id(토픽) 리스트:{}",dto);
		}
		
	}
	
	private CampaignRecord ConverToEntity(CampaignIdDTO dto) {
		CampaignRecord entity = new CampaignRecord();
		
		entity.setCampaignId(dto.getCampaingId());
		
		return entity;
		
	}
}
