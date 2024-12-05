package com.wnsud9771.controller.kafka;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.pipeline.PipelineIdDTO;
import com.wnsud9771.service.dashboard.SubmitGraphsdataService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/sendkafka")
@RequiredArgsConstructor
@Slf4j
public class ReceiveSucfilteringController {
	private final SubmitGraphsdataService submitGraphsdataService;
	
	@Operation(summary = "카프카 프로젝트에서 실행후 데이터들이 filteringdata에 쌓이면 그때 받는 api", description = "각자 데이터들 테이블로 하기")
	@PostMapping("/getsuc")
	 public ResponseEntity<PipelineIdDTO> kafkasendbestr(@RequestBody PipelineIdDTO dto) {
		log.info("필터링 작업다되고, 각 그래프db에 넣을 파이프라인 아이디 {}",dto.getPipelineid());
		submitGraphsdataService.findgraphstyleandsubmit(dto.getPipelineid());
        return ResponseEntity.ok(dto);
    }
}
