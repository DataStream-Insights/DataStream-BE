package com.wnsud9771.reoisitory.pipeline.data;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.data.FailDistinctData;

public interface FailDistinctDataRepository extends JpaRepository<FailDistinctData, Long>{
	void deleteByPipelinesId(Long pipelinesId);
}
