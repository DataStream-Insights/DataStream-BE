//package com.wnsud9771.service;
//
//import java.time.LocalDate;
//
//import org.springframework.stereotype.Service;
//
//import com.wnsud9771.component.CampaignIdGenerator;
//import com.wnsud9771.entity.Campaign;
//import com.wnsud9771.reoisitory.CampaignRepository;
//
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class CampaignIdService {
//	private final CampaignRepository campaignRepository;
//	private final CampaignIdGenerator campaignIdGenerator;
//	
//	 @Transactional
//	    public Campaign saveCampaign(Campaign campaign) {
//	        // ID 생성 및 설정
//	        campaign.setCampaign_id(campaignIdGenerator.generateUniqueId());
//	        
////	        // 기본값 설정
////	        if (campaign.getWrite_date() == null) {
////	        campaign.setWrite_date(LocalDate.now());
////	        }
//        
//	        return campaignRepository.save(campaign);
//	    }
//}
