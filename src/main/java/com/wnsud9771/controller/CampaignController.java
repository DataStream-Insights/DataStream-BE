package com.wnsud9771.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.CampaignDTO;
import com.wnsud9771.service.CampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
	
	 @Autowired
	 private CampaignService campaignService;

	    @GetMapping
	    public List<CampaignDTO> getAllCampaigns() {
	        return campaignService.getAllCampaigns();
	    }
	    
	    @PostMapping
	    public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
	        try {
	            CampaignDTO created = campaignService.createCampaign(campaignDTO);
	            return ResponseEntity.ok(created);
	        } catch (Exception e) {
	            return ResponseEntity.badRequest().build();
	        }
	    }
	    
//	    @PostMapping
//	    public Campaign createCampaign(@RequestBody Campaign campaign) {
//	        return campaignService.saveCampaign(campaign);
//	    }
	    
}
