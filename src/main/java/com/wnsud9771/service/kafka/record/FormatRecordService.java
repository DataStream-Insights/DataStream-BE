package com.wnsud9771.service.kafka.record;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.dto.format.FormatIdDTO;
import com.wnsud9771.dto.topic.CampainIdFormatIdDTO;
import com.wnsud9771.entity.recordentity.FormatRecord;
import com.wnsud9771.reoisitory.kafka.FormatRecordRepository;
import com.wnsud9771.service.kafka.topic.CreateFormatingTopicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@Service
@RequiredArgsConstructor
@Slf4j
public class FormatRecordService {
	private final FormatRecordRepository formatRecordRepository;
	private final CreateFormatingTopicService createFormatingTopicService;
	
	public void findNotSendFormatTopic(FormatIdDTO formatIddto, CampaignIdDTO campaignIddto) {
		
		FormatRecord entity = new FormatRecord();
		entity = ConverToEntity(formatIddto,campaignIddto);
		
		formatRecordRepository.save(entity);
		
		List<FormatRecord> formatRecords =formatRecordRepository.findAll();
		
		for (FormatRecord formatRecord : formatRecords) {
			try {
				CampainIdFormatIdDTO dto = new CampainIdFormatIdDTO();
				dto.setFormatId(formatRecord.getFormatId());
				dto.setCampaignId(campaignIddto.getCampaingId());
				if(!createFormatingTopicService.sendFormatTopic(dto)) {
					break;
				}
				formatRecordRepository.delete(formatRecord); // 전송되면 삭제
			}catch(Exception e) {
				log.error("포맷 토픽 전송 실패, formatrecord db에 campaignId, formatid 적재중");
			}
			
        }
		
		List<FormatRecord> NowFormatRecords =formatRecordRepository.findAll();
		for (FormatRecord nowFormatRecord : NowFormatRecords) {
			FormatIdDTO dto = new FormatIdDTO();
			dto.setFormatId(nowFormatRecord.getFormatId());
			log.info("전송 안된 포맷 id(토픽) 리스트:{}",dto);
		}
		
	}
	
	private FormatRecord ConverToEntity(FormatIdDTO dto, CampaignIdDTO campaignIddto) {
		FormatRecord entity = new FormatRecord();
		
		entity.setFormatId(dto.getFormatId());
		entity.setCampaignId(campaignIddto.getCampaingId());
		
		return entity;
		
	}
}
