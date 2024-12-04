package com.wnsud9771.service.pipeline;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.pipeline.search.GraphListDTO;
import com.wnsud9771.entity.dashboard.GraphList;
import com.wnsud9771.reoisitory.pipeline.GraphListRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FindGraphService {
	private final GraphListRepository graphListRepository; 
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
}
