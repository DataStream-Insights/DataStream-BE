package com.wnsud9771.dto;

import lombok.Data;

@Data
public class CampaignDTO {
	private String campaign_id;
    private String category1;
    private String category2;
    private String campaign_name;
    private String status;
    private String start_date;
    private String end_date;
    private String is_public;
    private String department;
    private String author;
    private String created_date;
}
