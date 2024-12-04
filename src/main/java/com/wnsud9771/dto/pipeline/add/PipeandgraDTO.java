package com.wnsud9771.dto.pipeline.add;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;

import lombok.Data;

@Data
public class PipeandgraDTO {
	
	private AddPipelineDTO dto;
	private List<GraphIdDTO> iddto;
}
