package com.wnsud9771.service.pipeline;

import org.springframework.stereotype.Service;

import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.Formatentity.FormatManagement;
import com.wnsud9771.entity.connect.CampaignConnect;
import com.wnsud9771.entity.connect.FilterConnect;
import com.wnsud9771.entity.connect.FormatConnect;
import com.wnsud9771.reoisitory.campaign.CampaignRepository;
import com.wnsud9771.reoisitory.connect.CampaignConnectRepository;
import com.wnsud9771.reoisitory.connect.FilterConnectRepository;
import com.wnsud9771.reoisitory.connect.FormatConnectRepository;
import com.wnsud9771.reoisitory.filter.FilterManagementRepository;
import com.wnsud9771.reoisitory.format.FormatManagementRepository;

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
	private final CampaignConnectRepository campaignConnectRepository;
	private final FormatConnectRepository formatConnectRepository;
	private final FilterConnectRepository filterConnectRepository;

	public CampaignConnect setconnectCampaign(String campaignId) {
		Campaign campaign = campaignRepository.findByCampaignId(campaignId)
				.orElseThrow(() -> new EntityNotFoundException("Campaign not found"));

		CampaignConnect entity = new CampaignConnect();
		entity.setCampaignKey(campaign.getId());

		campaignConnectRepository.save(entity);
		
		return entity;
	}

	public FormatConnect setconnectFormat(CampaignConnect campaignconnect, String formatId) {
		FormatManagement format = formatManagementRepository.findByFormatID(formatId)
				.orElseThrow(() -> new EntityNotFoundException("Format not found"));
		
		FormatConnect formatConnect = new FormatConnect();
		formatConnect.setFotmatKey(format.getId());
		
		formatConnect.setCampaignConnect(campaignconnect);

		formatConnectRepository.save(formatConnect);
		
		return formatConnect;
	}

	public void setconnectFilter(FormatConnect formatConnect, String filterId) {

		FilterManagement filter = filterManagementRepository.findByFilterManageId(filterId)
				.orElseThrow(() -> new EntityNotFoundException("Format not found"));

		FilterConnect entity = new FilterConnect();
		
		entity.setFilterKey(filter.getId());
		entity.setFormatConnect(formatConnect);

		filterConnectRepository.save(entity);

	}
}
