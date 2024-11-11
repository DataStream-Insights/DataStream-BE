package com.wnsud9771.reoisitory.mapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.kafka_topic.CampaignFormat;

public interface CampaignFormatRepository extends JpaRepository<CampaignFormat, Long>{
	List<CampaignFormat> findByCampaign_CampaignId(String campaignId);
}
