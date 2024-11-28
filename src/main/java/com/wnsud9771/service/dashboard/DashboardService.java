package com.wnsud9771.service.dashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.dashboard.ProcessDTO;
import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
	private final PipelinesRepository pipelinesRepository;
	
	//파이프라인 목록 보내기.
	public List<ProcessDTO> getprocessList() {
		
		return pipelinesRepository.findAll().stream().map(this::convertToprocessDTO).collect(Collectors.toList());
	}

	
	private ProcessDTO convertToprocessDTO(Pipelines pipelines) {
		ProcessDTO dto = new ProcessDTO();
		
		dto.setId(pipelines.getId());
		dto.setName(pipelines.getName());
		
		return dto;
	}
	
	
	
}
