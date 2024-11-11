package com.wnsud9771.controller.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.campaign.CampaignDTO;
import com.wnsud9771.dto.campaign.CampaignIdDTO;
import com.wnsud9771.service.Campaign.CampaignService;
import com.wnsud9771.service.sendkafka.CreateTopicService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/campaigns")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CampaignController {

	@Autowired
	private CampaignService campaignService;
	private CreateTopicService createTopicService;

	@Operation(summary = "캠페인 관리화면 전체 조회", description = "전체 아이템 목록을 조회합니다.")
	@GetMapping
	public List<CampaignDTO> getAllCampaigns() {
		return campaignService.getAllCampaigns();
	}

	@Operation(summary = "캠페인 관리화면 추가 + 카프카 프로젝트로 캠페인 토픽 이름 보냄", description = ".")
	@PostMapping("/add")
	public ResponseEntity<CampaignDTO> createCampaign(@RequestBody CampaignDTO campaignDTO) {
		try {
			log.info("Received DTO: {}", campaignDTO);
			CampaignDTO created = campaignService.createCampaign(campaignDTO);
			//topic 이름 추가
			CampaignIdDTO dto = new CampaignIdDTO();
			dto.setCampaingId(campaignDTO.getCampaignId());
			 createTopicService.sendCampaignTopic(dto);
			return ResponseEntity.ok(created);
		} catch (Exception e) {
			log.info("error message", e.getMessage());
			throw e;
		}
	}

}
