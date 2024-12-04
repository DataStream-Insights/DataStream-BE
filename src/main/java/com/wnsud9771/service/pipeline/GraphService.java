package com.wnsud9771.service.pipeline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.pipeline.add.GraphIdDTO;
import com.wnsud9771.dto.pipeline.search.GraphListDTO;
import com.wnsud9771.entity.dashboard.GraphList;
import com.wnsud9771.entity.dashboard.GraphPipelinesConnect;
import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.reoisitory.pipeline.GraphListRepository;
import com.wnsud9771.reoisitory.pipeline.GraphPipelinesConnectRepository;
import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphService {
	private final GraphListRepository graphListRepository; 
	private final GraphPipelinesConnectRepository graphPipelinesConnectRepository;
	private final PipelinesRepository pipelinesRepository;
	
	public List<GraphListDTO> findall() {
		
		return graphListRepository.findAll().stream().map(this::convertoDTO).collect(Collectors.toList());
	}
	
	
	private GraphListDTO convertoDTO(GraphList entity) {
		GraphListDTO dto = new GraphListDTO();
		
		dto.setId(entity.getId());
		dto.setGraph_name(entity.getGraphName());
		dto.setGraph_explain(entity.getGraphExplain());
		
		return dto;
	}
	
	
	public GraphIdDTO connectpipeandgra(String pipelineid, Long graphid) {
		GraphPipelinesConnect entity = new GraphPipelinesConnect();
		
		GraphList graphlist = graphListRepository.findById(graphid).get();
		Pipelines pipelines = pipelinesRepository.findByPipelineId(pipelineid);
		entity.setGraphList(graphlist);
		entity.setPipelines(pipelines);
		
		graphPipelinesConnectRepository.save(entity);
		GraphIdDTO dto = new GraphIdDTO();
		dto.setId(graphid);
		
		return dto;
		
	}
}
