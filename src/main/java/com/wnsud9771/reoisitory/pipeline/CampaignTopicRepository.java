package com.wnsud9771.reoisitory.pipeline;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.CampaignTopic;
import com.wnsud9771.entity.pipelineentity.Pipelines;

public interface CampaignTopicRepository extends JpaRepository<CampaignTopic, Long>{
	  CampaignTopic findByPipelines(Pipelines pipeline);
	  CampaignTopic findByPipelinesId(Long pipelinesId);
}
