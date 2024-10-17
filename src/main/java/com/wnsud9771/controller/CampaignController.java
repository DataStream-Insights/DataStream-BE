package com.wnsud9771.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.entity.Campaign;
import com.wnsud9771.service.CampaignService;

@RestController
@RequestMapping("/api/campaigns")
public class CampaignController {
	
	 @Autowired
	 private CampaignService campaignService;

	    @GetMapping
	    public List<Campaign> getAllCampaigns() {
	        return campaignService.getAllCampaigns();
	    }
	    
//	    @PostMapping
//	    public Campaign createCampaign(@RequestBody Campaign campaign) {
//	        return campaignService.saveCampaign(campaign);
//	    }
}
