package com.wnsud9771.controller.pipeline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/pipeline")
@RequiredArgsConstructor
@Slf4j
public class PipelineController {

	@Operation(summary = "파이프 라인 저장 ", description = ".")
	@PostMapping("/addpipeline")
	public ResponseEntity<AddPipelineDTO> addPipeline() {
		AddPipelineDTO dto = new AddPipelineDTO();

		return ResponseEntity.ok(dto);

	}

	@Operation(summary = "파이프라인 리스트 가져오기", description = ".")
	@GetMapping("/getpipeline")
	public ResponseEntity<AddPipelineDTO> getAllpipeline() {
		AddPipelineDTO dto = new AddPipelineDTO();

		return ResponseEntity.ok(dto);
	}

	@Operation(summary = "파이프라인 상세 조회", description = ".")
	@GetMapping("/getpipeline/{id}")
	public void getpipelinebyid() {

	}

}
