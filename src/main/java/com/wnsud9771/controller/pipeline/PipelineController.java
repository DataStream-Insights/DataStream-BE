package com.wnsud9771.controller.pipeline;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.err.ErrorMessageDTO;
import com.wnsud9771.dto.pipeline.ProcessStartDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;
import com.wnsud9771.dto.pipeline.add.GraphIdDTO;
import com.wnsud9771.dto.pipeline.add.PipeandgraDTO;
import com.wnsud9771.dto.pipeline.search.GraphListDTO;
import com.wnsud9771.dto.pipeline.search.PipelinesDTO;
import com.wnsud9771.dto.pipeline.search.SearchPipelineDTO;
import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;
import com.wnsud9771.service.pipeline.ConvertSendTopicsService;
import com.wnsud9771.service.pipeline.PipeGraphService;
import com.wnsud9771.service.pipeline.PipelineService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/pipeline")
@RequiredArgsConstructor
@Slf4j
public class PipelineController {
	private final PipelineService pipelineService;
	
	
	//private final PiplineStatusTestService piplineStatusTestService;
	private final PipelinesRepository pipelinesRepository;
	private final ConvertSendTopicsService convertSendTopicsService;
	private final PipeGraphService pipeGraphService;

	@Operation(summary = "파이프 라인 저장 ", description = ".")
	@PostMapping("/addpipeline")
	public ResponseEntity<List<GraphIdDTO>> addPipeline(@RequestBody PipeandgraDTO rece) {
		log.info(" 보기{}",rece);
		
		AddPipelineDTO dto = rece.getDto();
		AddPipelineDTO submitdto = pipelineService.submitpipeline(dto);
		List<GraphIdDTO> iddto = rece.getIddto();
		List<GraphIdDTO> suc = pipeGraphService.connectpipeandgra(submitdto.getPipelineId(),iddto);
		return ResponseEntity.ok(suc);

		
	}
	@Operation(summary = "파이프 라인 생성부분에서  그래프 목록 보기 ", description = ".")
	@GetMapping("/graphList")
	public ResponseEntity<List<GraphListDTO>> addPipeline() {
		
		return ResponseEntity.ok(pipeGraphService.findall());

		
	}
	
	@Operation(summary = "파이프 라인 삭제 ", description = ".")
	@PostMapping("/delpipeline")
	public ResponseEntity<ErrorMessageDTO> delPipeline(@RequestBody Long id) {
		boolean del = pipelineService.delpipelineAndall(id);
		log.info("{} 파이프라인 삭제요청받음", id);
		
		if(del==true) {
			ErrorMessageDTO dto = new ErrorMessageDTO();
			dto.setMessage("파이프라인 삭제 완료");
			return ResponseEntity.ok(dto);
		}else {
			ErrorMessageDTO dto = new ErrorMessageDTO();
			dto.setMessage("파이프라인 삭제 안됨.");
			return ResponseEntity.ok(dto);
		}

	}

	@Operation(summary = "파이프라인 리스트 가져오기", description = ".")
	@GetMapping("/getpipeline")
	public ResponseEntity<List<PipelinesDTO>> getAllpipeline() {
		return ResponseEntity.ok(pipelineService.findpipelinelist());
	}

	@Operation(summary = "파이프라인 상세 조회", description = ".")
	@GetMapping("/getpipeline/{id}")
	public ResponseEntity<SearchPipelineDTO> getpipelinebyid(@PathVariable Long id) {
		SearchPipelineDTO dto = new SearchPipelineDTO();
		dto = pipelineService.findpipelinebykeyidwithname(id);
		return ResponseEntity.ok(dto);
	}
	
	@Operation(summary = "파이프라인 상세 조회에서 그래프 부분만 따로보여주기", description = "상세보기할 파이프라인 id임!")
	@GetMapping("/getpipelinesgraph/{id}")
	public ResponseEntity<List<GraphListDTO>> getgraphbyid(@PathVariable Long id) {
		List<GraphListDTO> suc = pipeGraphService.viewdetailpipesgraph(id);
		log.info("보내는 suc {}",suc);
		return ResponseEntity.ok(suc);
	}

	@Operation(summary = "파이프라인 실행시키거나 중지시키거나 ", description = "boolean타입으로 실행할거면 true, 중지시킬거면 false 실행과 중지는 버튼눌러서 하는식으로?")
	@PostMapping("/processExecutable")
	public ResponseEntity<ProcessStartDTO> processcontrol(@RequestBody ProcessStartDTO dto) {

		log.info("프론트에서 요청한 파이프라인 {} ,{}", dto.getId(), dto.isExecutable());
		return ResponseEntity.ok(pipelineService.processStartControl(dto));
	}

//	@Operation(summary = "파이프라인 실행시키거나 중지시키거나 ", description = "boolean타입으로 실행할거면 true, 중지시킬거면 false 실행과 중지는 버튼눌러서 하는식으로?")
//	@PostMapping("/processExecutable")
//	public ResponseEntity<ProcessStartDTO> processcontrol(@RequestBody ProcessStartDTO dto){
//		//log.info("dto.get id {}",dto.getId());
//		//Optional<Pipelines> entity = pipelinesRepository.findById(dto.getId());
//		//entity.get().setExecutable(dto.isExecutable());
//		
//		//log.info("찾은 엔티티 {}", entity.get().getPipelineId());
//
//		AddPipelineDTO topics = convertSendTopicsService.findpipelinebykeyid(dto.getId());
//		
//
//		if (dto.isExecutable() == true) { // true면 실행하게 해서 이벤트 발생시켜서 토픽들 생성하고, 컨슈머 생성까지하게
//			log.info("이벤트 발생하려는 파이프라인 {}",topics.getPipelineId());
//			piplineStatusTestService.testStatus(topics);
//		}else {
//			piplineStatusTestService.testStatus2(topics);
//		}
//		log.info("프론트에서 요청한 파이프라인 {} ,{}",dto.getId(), dto.isExecutable());
//		return ResponseEntity.ok(dto);
//	}
}
