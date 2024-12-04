package com.wnsud9771.reoisitory.pipeline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.GraphPipelinesConnect;
import com.wnsud9771.entity.pipelineentity.Pipelines;

public interface GraphPipelinesConnectRepository  extends JpaRepository<GraphPipelinesConnect, Long>{
	 List<GraphPipelinesConnect> findGraphListByPipelinesId(Long pipelineId);
	 
	 void deleteByPipelines(Pipelines pipelines);
}
