package com.wnsud9771.service.Campaign;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.campaign.CampaignDTO;
import com.wnsud9771.dto.campaign.ResearchCampaignDTO;
import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.Campaignentity.Category1;
import com.wnsud9771.entity.Campaignentity.Category2;
import com.wnsud9771.reoisitory.author.AuthorRepository;
import com.wnsud9771.reoisitory.campaign.CampaignRepository;
import com.wnsud9771.reoisitory.campaign.Category1Repository;
import com.wnsud9771.reoisitory.campaign.Category2Repository;
import com.wnsud9771.reoisitory.department.DepartmentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CampaignService {
	private final CampaignRepository campaignRepository;
	private final Category1Repository category1Repository;
	private final Category2Repository category2Repository;
	private final DepartmentRepository departmentRepository;
	private final AuthorRepository authorRepository;

	// 기존 메서드들
	public List<ResearchCampaignDTO> getAllCampaigns() {
		return campaignRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	public ResearchCampaignDTO selectfindcampaign(Long id) {
		ResearchCampaignDTO dto = new ResearchCampaignDTO();
		
		Campaign campaign = campaignRepository.findById(id).get();
		dto.setCampaignDescription(campaign.getCampaign_description());
		dto.setCampaignId(campaign.getCampaignId());
		dto.setCampaignName(campaign.getCampaign_name());
		dto.setVisibility(campaign.getIs_public());
		dto.setStartDate(campaign.getStart_date().toString());
		dto.setEndDate(campaign.getEnd_date().toString());
		dto.setCampaignClassification1(campaign.getCategory2().getCategory1().getCategory1());
		dto.setCampaignClassification2(campaign.getCategory2().getCategory2());
		
		return dto; 
	}

	private ResearchCampaignDTO convertToDTO(Campaign campaign) {
		ResearchCampaignDTO dto = new ResearchCampaignDTO();
		dto.setId(campaign.getId());
		dto.setCampaignId(campaign.getCampaignId());
		Category1 category1 = campaign.getCategory2().getCategory1();
		dto.setCampaignClassification1(category1.getCategory1());
		Category2 category2 = campaign.getCategory2();
		dto.setCampaignClassification2(category2.getCategory2());
		dto.setCampaignName(campaign.getCampaign_name());
//		dto.setStatus(campaign.getStatus());
//		dto.setCustomerType(campaign.getCustomerType());
		dto.setStartDate(campaign.getStart_date().toString());
		dto.setEndDate(campaign.getEnd_date().toString());
		dto.setVisibility(campaign.getIs_public());
		dto.setCampaignDescription(campaign.getCampaign_description());
//		Department department = campaign.getDepartment();
//		dto.setDepartment(department.getDepartment());
//		Author author = campaign.getAuthor();
//		dto.setAuthor(author.getAuthor());
		dto.setCreatedDate(campaign.getWrite_date().toString());
		return dto;
	}

	// 새로 추가하는 메서드들
	public void createCampaign(CampaignDTO dto) {
		// DTO를 엔티티로 변환
		Campaign campaign = convertToEntity(dto);

		// 엔티티 저장
		Campaign savedCampaign = campaignRepository.save(campaign);


	}

	private Campaign convertToEntity(CampaignDTO dto) {
		Campaign campaign = new Campaign();
//		campaign.setId(null);
		// 기본 필드 설정
		campaign.setCampaignId(dto.getCampaignId());
		campaign.setCampaign_name(dto.getCampaignName());
//		campaign.setStatus(dto.getStatus());
		campaign.setIs_public(dto.getVisibility());
		campaign.setCampaign_description(dto.getCampaignDescription());
//		campaign.setEnd_date_after(dto.getEndAfter());
//		campaign.setCustomerType(dto.getCustomerType());
//        Department department = new Department();
//        department.setDepartment(dto.getDepartment());
//        Author author = new Author();
//        author.setAuthor(dto.getAuthor());

		// 날짜 변환
		campaign.setStart_date(LocalDate.parse(dto.getStartDate()));
		campaign.setEnd_date(LocalDate.parse(dto.getEndDate()));
		campaign.setWrite_date(LocalDate.parse(dto.getCreatedDate()));

//        // Category1 설정
//        Category1 category1 = category1Repository.findByCategory1(dto.getCampaignClassification1())
//            .orElseThrow(() -> new RuntimeException("Category1 not found: " + dto.getCampaignClassification1()));
//        campaign.setCategory1(category1);
//         

		// Category2 설정
		Category2 category2 = category2Repository.findById(Long.parseLong(dto.getCampaignClassification2()))
				.orElseThrow(() -> new RuntimeException("Category2 not found: " + dto.getCampaignClassification2()));
		campaign.setCategory2(category2);

//		Department department = departmentRepository.findById(Long.parseLong(dto.getDepartment()))
//				.orElseThrow(() -> new RuntimeException("Department not found: " + dto.getDepartment()));
//		campaign.setDepartment(department);
//
//		Author author = authorRepository.findById(Long.parseLong(dto.getAuthor()))
//				.orElseThrow(() -> new RuntimeException("Author not found: " + dto.getAuthor()));
//		campaign.setAuthor(author);
		return campaign;
	}
}