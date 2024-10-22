package com.wnsud9771.dto;

import lombok.Data;

@Data
public class CampaignDTO {
	private String campaignId;
    private String category1;
    private String category2;
    private String campaignName;
    private String status;
    private String startDate;
    private String endDate;
    private String isPublic;
    private String department;
    private String author;
    private String createdDate;
}
