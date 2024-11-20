package com.wnsud9771.reoisitory.pipeline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.CampaignTopic;
import com.wnsud9771.entity.pipelineentity.FormatTopic;

public interface FormatTopicRepository extends JpaRepository<FormatTopic, Long>{
	 List<FormatTopic> findByCampaignTopic(CampaignTopic campaignTopic);
	
}
