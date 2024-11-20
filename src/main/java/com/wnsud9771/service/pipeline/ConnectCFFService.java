package com.wnsud9771.service.pipeline;

import org.springframework.stereotype.Service;

import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.Formatentity.FormatManagement;
import com.wnsud9771.entity.kafka_topic.CampaignFormat;
import com.wnsud9771.entity.kafka_topic.FormatFilter;
import com.wnsud9771.reoisitory.campaign.CampaignRepository;
import com.wnsud9771.reoisitory.filter.FilterManagementRepository;
import com.wnsud9771.reoisitory.format.FormatManagementRepository;
import com.wnsud9771.reoisitory.mapping.CampaignFormatRepository;
import com.wnsud9771.reoisitory.mapping.FormatFilterRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConnectCFFService {
	private final CampaignRepository campaignRepository;
	private final FormatManagementRepository formatManagementRepository;
	private final FilterManagementRepository filterManagementRepository;
	private final CampaignFormatRepository campaignFormatRepository;
	private final FormatFilterRepository formatFilterRepository;
	
	public void connectCampaignAndFormat(String campaignId, String formatId) {
		 Campaign campaign = campaignRepository.findByCampaignId(campaignId)
			        .orElseThrow(() -> new EntityNotFoundException("Campaign not found"));
			    
			    FormatManagement format = formatManagementRepository.findByFormatID(formatId)
			        .orElseThrow(() -> new EntityNotFoundException("Format not found"));
			    
			    // CampaignFormat 객체 생성 및 연결
			    CampaignFormat campaignFormat = new CampaignFormat();
			    campaignFormat.setCampaign(campaign);
			    campaignFormat.setFormatManagement(format);
			    
			    campaignFormatRepository.save(campaignFormat);

	}
	
	public void connectFormatAndFilter(String formatId, String filterId) {
		FormatManagement format = formatManagementRepository.findByFormatID(formatId)
		        .orElseThrow(() -> new EntityNotFoundException("Format not found"));
		
			    
			    FilterManagement filter = filterManagementRepository.findByFilterManageId(filterId)
			        .orElseThrow(() -> new EntityNotFoundException("Format not found"));
			    
			    FormatFilter formatFilter = new FormatFilter();
			    formatFilter.setFormatManagement(format);
			    formatFilter.setFilterManagement(filter);
			    
			    formatFilterRepository.save(formatFilter);
			    
	}
}
