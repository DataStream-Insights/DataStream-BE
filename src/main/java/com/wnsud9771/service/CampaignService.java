package com.wnsud9771.service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.CampaignDTO;
import com.wnsud9771.entity.Campaign;
import com.wnsud9771.entity.Category1;
import com.wnsud9771.entity.Category2;
import com.wnsud9771.reoisitory.CampaignRepository;
import com.wnsud9771.reoisitory.Category1Repository;
import com.wnsud9771.reoisitory.Category2Repository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignService {
    private final CampaignRepository campaignRepository;
    private final Category1Repository category1Repository;
    private final Category2Repository category2Repository;
    
    // 기존 메서드들
    public List<CampaignDTO> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CampaignDTO convertToDTO(Campaign campaign) {
        CampaignDTO dto = new CampaignDTO();
        dto.setCampaignId(campaign.getCampaign_id());
        Category1 category1 = campaign.getCategory1();
        dto.setCategory1(category1.getCategory1());
        Category2 category2 = campaign.getCategory2();
        dto.setCategory2(category2.getCategory2());
        dto.setCampaignName(campaign.getCampaign_name());
        dto.setStatus(campaign.getStatus());
        dto.setStartDate(campaign.getStart_date().toString());
        dto.setEndDate(campaign.getEnd_date().toString());
        dto.setIsPublic(campaign.getIs_public());
        dto.setDepartment(campaign.getDepartment());
        dto.setAuthor(campaign.getAuthor());
        dto.setCreatedDate(campaign.getWrite_date().toString());
        return dto;
    }

    // 새로 추가하는 메서드들
    public CampaignDTO createCampaign(CampaignDTO dto) {
        // DTO를 엔티티로 변환
        Campaign campaign = convertToEntity(dto);
        
        // 엔티티 저장
        Campaign savedCampaign = campaignRepository.save(campaign);
        
        // 저장된 엔티티를 다시 DTO로 변환하여 반환
        return convertToDTO(savedCampaign);
    }

    private Campaign convertToEntity(CampaignDTO dto) {
        Campaign campaign = new Campaign();
        
        // 기본 필드 설정
        campaign.setCampaign_id(dto.getCampaignId());
        campaign.setCampaign_name(dto.getCampaignName());
        campaign.setStatus(dto.getStatus());
        campaign.setIs_public(dto.getIsPublic());
        campaign.setDepartment(dto.getDepartment());
        campaign.setAuthor(dto.getAuthor());
        
        // 날짜 변환
        campaign.setStart_date(LocalDate.parse(dto.getStartDate()));
        campaign.setEnd_date(LocalDate.parse(dto.getEndDate()));
        campaign.setWrite_date(LocalDate.parse(dto.getCreatedDate()));
        
        // Category1 설정
        Category1 category1 = category1Repository.findByCategory1(dto.getCategory1())
            .orElseThrow(() -> new RuntimeException("Category1 not found: " + dto.getCategory1()));
        campaign.setCategory1(category1);
        
        // Category2 설정
        Category2 category2 = category2Repository.findByCategory2(dto.getCategory2())
            .orElseThrow(() -> new RuntimeException("Category2 not found: " + dto.getCategory2()));
        campaign.setCategory2(category2);
        
        return campaign;
    }
}