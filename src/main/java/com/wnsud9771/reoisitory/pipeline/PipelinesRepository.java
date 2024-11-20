package com.wnsud9771.reoisitory.pipeline;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.Pipelines;

public interface PipelinesRepository extends JpaRepository<Pipelines, Long>{
	Pipelines findByPipelineId(String pipelineId);
}
