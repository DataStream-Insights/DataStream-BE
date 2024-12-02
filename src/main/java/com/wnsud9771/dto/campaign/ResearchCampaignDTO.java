package com.wnsud9771.dto.campaign;

import lombok.Data;

@Data
public class ResearchCampaignDTO {
	private Long id; //기본 key 아무거나 보내도됨. 받는걸로 설정안함. db저장할때 자동생성. dto형식때메 존재.
	private String campaignId; // 캠페인 id . 프론트에서 만들때 보내주는 id
	private String campaignClassification1; // 캠페인 분류 1 (대분류)
    private String campaignClassification2; // 캠페인분류 2 (소분류)
    private String campaignName; //캠페인 이름 
    //private String customerType;
    //private String status;
    private String campaignDescription; //캠페인 상세설명
    private String startDate; //시작날짜
    private String endDate; //종료날짜
    //private Long endAfter;
    private String visibility; //공개설정 
    //private String department; //부서
    //private String author; //기안자
    private String createdDate; //캠페인 만든 날짜
    //private String tags; 
}
