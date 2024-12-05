package com.wnsud9771.reoisitory.pipeline.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.entity.pipelineentity.data.FailFilteringData;

public interface FailFilteringDataRepository extends JpaRepository<FailFilteringData, Long>{
	List<FailFilteringData> findByPipelines(Pipelines pipelines);
}
