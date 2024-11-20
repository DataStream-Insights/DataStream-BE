package com.wnsud9771.controller.pipeline;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.pipeline.ProcessStartDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;
import com.wnsud9771.dto.pipeline.search.PipelinesDTO;
import com.wnsud9771.service.pipeline.PipelineService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pipeline")
@RequiredArgsConstructor
@Slf4j
public class PipelineController {
	private final PipelineService pipelineService;

	@Operation(summary = "파이프 라인 저장 ", description = ".")
	@PostMapping("/addpipeline")
	public ResponseEntity<AddPipelineDTO> addPipeline(@RequestBody AddPipelineDTO dto) {
		pipelineService.submitpipeline(dto);
		return ResponseEntity.ok(dto);

	}

	@Operation(summary = "파이프라인 리스트 가져오기", description = ".")
	@GetMapping("/getpipeline")
	public ResponseEntity<List<PipelinesDTO>> getAllpipeline() {
		return ResponseEntity.ok(pipelineService.findpipelinelist());
	}

	@Operation(summary = "파이프라인 상세 조회", description = ".")
	@GetMapping("/getpipeline/{id}")
	public ResponseEntity<AddPipelineDTO> getpipelinebyid(@PathVariable Long id) {
		AddPipelineDTO dto = new AddPipelineDTO();
		dto = pipelineService.findpipelinebykeyid(id);
		return ResponseEntity.ok(dto);
	}
	
	@Operation(summary = "파이프라인 실행 ", description = ".")
	@PostMapping("/processStart")
	public ResponseEntity<ProcessStartDTO> processcontrol(@RequestBody ProcessStartDTO dto){
	
		return ResponseEntity.ok(pipelineService.processStartControl(dto));
	}
}
