package com.wnsud9771.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.CampaignDTO;
import com.wnsud9771.entity.Author;
import com.wnsud9771.entity.Campaign;
import com.wnsud9771.entity.Category1;
import com.wnsud9771.entity.Category2;
import com.wnsud9771.entity.Department;
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
	
	public List<CampaignDTO> getAllCampaigns() {
        return campaignRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CampaignDTO convertToDTO(Campaign campaign) {
        CampaignDTO dto = new CampaignDTO();
        dto.setNo(campaign.getId());
        dto.setCampaignId(campaign.getCampaign_id());
        Category1 category1 = campaign.getCategory2().getCategory1();
        dto.setCampaignClassification1(category1.getCategory1());
        Category2 category2 = campaign.getCategory2();
        dto.setCampaignClassification2(category2.getCategory2());
        dto.setCampaignName(campaign.getCampaign_name());
        dto.setCustomerType(campaign.getStatus());
        dto.setStartDate(campaign.getStart_date().toString());
        dto.setEndDate(campaign.getEnd_date().toString());
        dto.setVisibility(campaign.getIs_public());
        Department department = campaign.getDepartment();
        dto.setDepartment(department.getDepartment());
        Author author = campaign.getAuthor();
        dto.setAuthor(author.getAuthor());
        dto.setCreatedDate(campaign.getWrite_date().toString());
        return dto;
    }
	
    


//    public Campaign saveCampaign(Campaign campaign) {
//        return campaignRepository.save(campaign);
//    }
}
