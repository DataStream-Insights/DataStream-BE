package com.wnsud9771.reoisitory.pipeline.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.data.DistinctData;

public interface DistinctDataRepository extends JpaRepository<DistinctData, Long>{
	void deleteByPipelinesId(Long pipelinesId);
}
