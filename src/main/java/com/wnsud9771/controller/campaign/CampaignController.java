package com.wnsud9771.controller.campaign;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.campaign.CampaignDTO;
import com.wnsud9771.event.CampaignCreatedEvent;
import com.wnsud9771.service.Campaign.CampaignService;
import com.wnsud9771.service.kafka.topic.CreateCampaignTopicService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/campaigns")
@Slf4j
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CampaignController {

	@Autowired
	private CampaignService campaignService;
	private CreateCampaignTopicService createTopicService;
	private final ApplicationEventPublisher eventPublisher;

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
			
			//topic 이름 추가 이벤트 발행 방식으로 바꿈
			//CampaignIdDTO dto = new CampaignIdDTO();
			//dto.setCampaingId(campaignDTO.getCampaignId());
			//createTopicService.sendCampaignTopic(dto);
			
			 eventPublisher.publishEvent(new CampaignCreatedEvent(this, created.getCampaignId()));
			
			return ResponseEntity.ok(created);
		} catch (Exception e) {
			log.info("error message", e.getMessage());
			throw e;
		}
	}

}
