package com.wnsud9771.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import com.wnsud9771.dto.CampaignDTO;
import com.wnsud9771.service.CampaignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/campaigns")
@Slf4j
public class CampaignController {

	@Autowired
	private CampaignService campaignService;

	@GetMapping
	public List<CampaignDTO> getAllCampaigns() {
		return campaignService.getAllCampaigns();
	}

	@PostMapping("/add")
	public RedirectView createCampaign(@RequestBody CampaignDTO campaignDTO) {
		try {
			// campaignDTO.setNo(null);
			log.info("Received DTO: {}", campaignDTO);
			CampaignDTO created = campaignService.createCampaign(campaignDTO);
			return new RedirectView("/campaigns");
		} catch (Exception e) {
			log.info("error message", e.getMessage());
			return new RedirectView("/add");
		}
	}
	    
	    
//	    @PostMapping
//	    public Campaign createCampaign(@RequestBody Campaign campaign) {
//	        return campaignService.saveCampaign(campaign);
//	    }

}
