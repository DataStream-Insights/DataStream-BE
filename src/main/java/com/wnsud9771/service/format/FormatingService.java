//package com.wnsud9771.service.format;
//
//import org.springframework.stereotype.Service;
//
//import com.wnsud9771.dto.filter.FilterItemDTO;
//import com.wnsud9771.dto.format.FormatDTO;
//import com.wnsud9771.entity.Authorentity.Author;
//import com.wnsud9771.entity.Campaignentity.Category1;
//import com.wnsud9771.entity.Campaignentity.Category2;
//import com.wnsud9771.entity.Campaignentity.Department;
//import com.wnsud9771.entity.FIlterentity.FilterItem;
//import com.wnsud9771.entity.Formatentity.FormatManagement;
//import com.wnsud9771.reoisitory.filter.FilterItemRepository;
//import com.wnsud9771.reoisitory.format.FormatManagementRepository;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class FormatingService {
//	private final FormatManagementRepository formatRepository;
//	private final FilterItemRepository filterItemRepository;
//	
//	
//	
//	private FormatDTO convertToDTO1(FormatManagement format) {
//		FormatDTO dto = new FormatDTO();
//		dto.setFormatID(format.getFormatID()); //formatID
//		dto.setFormatname(format.getFormatname()); //format명
//		//dto.setId(format.getId());
//		
////		dto.setNo(campaign.getId());
////		dto.setCampaignId(campaign.getCampaign_id());
////		Category1 category1 = campaign.getCategory2().getCategory1();
////		dto.setCampaignClassification1(category1.getCategory1());
////		Category2 category2 = campaign.getCategory2();
////		dto.setCampaignClassification2(category2.getCategory2());
////		dto.setCampaignName(campaign.getCampaign_name());
////		dto.setStatus(campaign.getStatus());
////		dto.setCustomerType(campaign.getCustomerType());
////		dto.setStartDate(campaign.getStart_date().toString());
////		dto.setEndDate(campaign.getEnd_date().toString());
////		dto.setVisibility(campaign.getIs_public());
////		dto.setCampaignDescription(campaign.getCampaign_description());
////		Department department = campaign.getDepartment();
////		dto.setDepartment(department.getDepartment());
////		Author author = campaign.getAuthor();
////		dto.setAuthor(author.getAuthor());
////		dto.setCreatedDate(campaign.getWrite_date().toString());
//		return dto;
//	}
//	
//	private FilterItemDTO convertToDTO2(FilterItem filterItem) {
//		FilterItemDTO dto = new FilterItemDTO();
//		
//		//dto.setItem_name(filterItem.getItem_name()); //item_name; //아이템 명
//		dto.setItem_alias(filterItem.getItem_alias()); //item_alias; //아이템 별명
//		//dto.setItem_contents_ex(filterItem.getItem_contents_ex());
//		dto.setItem_explain(filterItem.getItem_explain()); //item_explain; //아이템 설명
//		dto.setItem_type(filterItem.getItem_type()); //item_type; //TYPE
//		
//		return dto;
//	}
//}
