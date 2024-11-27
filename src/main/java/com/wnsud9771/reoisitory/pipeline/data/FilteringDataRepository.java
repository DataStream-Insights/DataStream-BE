package com.wnsud9771.reoisitory.pipeline.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.entity.pipelineentity.data.FilteringData;

public interface FilteringDataRepository extends JpaRepository<FilteringData, Long>{
	List<FilteringData> findByPipelines(Pipelines pipelines);
}
