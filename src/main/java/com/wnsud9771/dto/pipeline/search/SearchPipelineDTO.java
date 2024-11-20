package com.wnsud9771.dto.pipeline.search;

import lombok.Data;

@Data
public class SearchPipelineDTO {
	
	private String pipelineName;
	private String pipelineId;
	
	private SearchCampaignTopicDTO searchCampaignTopic;
	
}
