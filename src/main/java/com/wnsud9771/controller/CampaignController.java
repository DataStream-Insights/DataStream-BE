package com.wnsud9771.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.campaign.CampaignDTO;
import com.wnsud9771.service.Campaign.CampaignService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/campaigns")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CampaignController {

	@Autowired
	private CampaignService campaignService;

	@GetMapping
	public List<CampaignDTO> getAllCampaigns() {
		return campaignService.getAllCampaigns();
	}

	@PostMapping("/add")
	public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
		try {
			log.info("Received DTO: {}", campaignDTO);
			CampaignDTO created = campaignService.createCampaign(campaignDTO);
			Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("data", created);
            response.put("message", "Campaign created successfully");
			return ResponseEntity.ok(created);
		} catch (Exception e) {
			log.info("error message", e.getMessage());
			throw e;
		}
	}
	    


}
