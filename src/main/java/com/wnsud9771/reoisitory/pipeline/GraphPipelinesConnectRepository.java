package com.wnsud9771.reoisitory.pipeline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.GraphList;
import com.wnsud9771.entity.dashboard.GraphPipelinesConnect;

public interface GraphPipelinesConnectRepository  extends JpaRepository<GraphPipelinesConnect, Long>{
	 List<GraphList> findGraphListByPipelinesId(Long pipelineId);
}
