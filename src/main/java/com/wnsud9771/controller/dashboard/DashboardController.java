package com.wnsud9771.controller.dashboard;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.dashboard.ProcessDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
	@Operation(summary = "프로세스 목록보기 ", description = ".")
	@GetMapping("/processes")
	public ResponseEntity<ProcessDTO> findProcesses() {
		return ResponseEntity.ok(dto);

		
	}
}
