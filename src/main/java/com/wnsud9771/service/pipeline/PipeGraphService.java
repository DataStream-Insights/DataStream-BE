package com.wnsud9771.service.pipeline;

import java.util.ArrayList;
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
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class PipeGraphService {
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

	public List<GraphIdDTO> connectpipeandgra(String pipelineid, List<GraphIdDTO> graphids) {

		List<GraphIdDTO> dtos = new ArrayList<>();

		for (GraphIdDTO graphiddto : graphids) {
			GraphPipelinesConnect entity = new GraphPipelinesConnect();
			Long graphid = graphiddto.getId();

			GraphList graphlist = graphListRepository.findById(graphid).get();
			Pipelines pipelines = pipelinesRepository.findByPipelineId(pipelineid);
			entity.setGraphList(graphlist);
			entity.setPipelines(pipelines);

			graphPipelinesConnectRepository.save(entity);
			dtos.add(graphiddto);
			log.info("연결시킨 그래프 아이디 {}", graphiddto);
		}

		return dtos;

	}

	public List<GraphListDTO> viewdetailpipesgraph(Long id) {
		List<GraphList> entities = graphPipelinesConnectRepository.findGraphListByPipelinesId(id);
		List<GraphListDTO> dtos = new ArrayList<>();
		for (GraphList entity : entities) {
			GraphListDTO dto = convertoDTO(entity);
			dtos.add(dto);
		}

		return dtos;
	}
}
