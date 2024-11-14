package com.wnsud9771.service.kafka.record;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.dto.filter.FilterIdDTO;
import com.wnsud9771.dto.format.FormatIdDTO;
import com.wnsud9771.dto.topic.CampaignIdFormatIdFilterIdDTO;
import com.wnsud9771.entity.recordentity.FilterRecord;
import com.wnsud9771.entity.recordentity.FormatRecord;
import com.wnsud9771.reoisitory.kafka.FilterRecordRepository;
import com.wnsud9771.service.kafka.topic.CreateFilterTopicService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class FilterRecordService {
	private final FilterRecordRepository filterRecordRepository;
	private final CreateFilterTopicService createFilterTopicService;
	
	public void findNotSendFormatTopic(FilterIdDTO filterIdDTO,FormatIdDTO formatIdDTO, String campaignId) {
		FilterRecord entity = new FilterRecord();
		entity = ConverToEntity(filterIdDTO, formatIdDTO);
		
		filterRecordRepository.save(entity);
		
		List<FilterRecord> filterRecords =filterRecordRepository.findAll();
		
		for (FilterRecord filterRecord: filterRecords) {
			try {
				CampaignIdFormatIdFilterIdDTO  dto = new CampaignIdFormatIdFilterIdDTO ();
				dto.setFilterId(filterRecord.getFilterId());
				dto.setFormatId(filterRecord.getFormatId());
				dto.setCampaignId(campaignId);
				if(!createFilterTopicService.sendFilterTopic(dto)) {
					break;
				}
				filterRecordRepository.delete(filterRecord); // 전송되면 삭제
			}catch(Exception e) {
				log.error("포맷 토픽 전송 실패, formatrecord db에 campaignId, formatid 적재중");
			}
			
        }
		
		List<FilterRecord> NowFilterRecords =filterRecordRepository.findAll();
		for (FilterRecord nowFilterRecord : NowFilterRecords) {
			FilterIdDTO dto = new FilterIdDTO();
			dto.setFilterId(nowFilterRecord.getFilterId());
			log.info("전송 안된 필터 id(토픽) 리스트:{}",dto);
		}
	}
	
	private FilterRecord ConverToEntity(FilterIdDTO filterIdDTO,FormatIdDTO formatIdDTO) {
		FilterRecord entity = new FilterRecord();
		
		entity.setFilterId(filterIdDTO.getFilterId());
		entity.setFormatId(formatIdDTO.getFormatId());
		
		return entity;
		
	}
}
