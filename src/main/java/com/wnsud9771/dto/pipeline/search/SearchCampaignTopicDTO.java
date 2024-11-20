package com.wnsud9771.dto.pipeline.search;

import java.util.List;

import lombok.Data;

@Data
public class SearchCampaignTopicDTO {
	
	private String campaignId;
	private String campaignName;
	
	private List<SearchFormatTopicDTO> searchFormatTopics;
}
