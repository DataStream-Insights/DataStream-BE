package com.wnsud9771.dto.campaign;

import lombok.Data;

@Data
public class CampaignDTO {
	private Long no;
	private String campaignId;
	private String campaignClassification1;
    private String campaignClassification2;
    private String campaignName;
    private String customerType;
    private String status;
    private String campaignDescription;
    private String startDate;
    private String endDate;
    private Long endAfter;
    private String visibility;
    private String department;
    private String author;
    private String createdDate;
    private String tags;
}
