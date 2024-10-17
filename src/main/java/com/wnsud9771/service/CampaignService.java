package com.wnsud9771.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.entity.Campaign;
import com.wnsud9771.reoisitory.CampaignRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignService {
	private final CampaignRepository campaignRepository;
	
	
    public List<Campaign> getAllCampaigns() {
        return campaignRepository.findAll();
    }

//    public Campaign saveCampaign(Campaign campaign) {
//        return campaignRepository.save(campaign);
//    }
}
