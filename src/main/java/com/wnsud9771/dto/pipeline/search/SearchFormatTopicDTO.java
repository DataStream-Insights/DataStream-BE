package com.wnsud9771.dto.pipeline.search;

import java.util.List;

import lombok.Data;

@Data
public class SearchFormatTopicDTO {
	private String formatId;
	private String formatName;
	
	private List<SearchFilterTopicDTO> searchFilterTopics;
}
