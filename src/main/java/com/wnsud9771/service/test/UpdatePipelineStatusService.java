//package com.wnsud9771.service.test;
//
//import org.springframework.stereotype.Service;
//
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class UpdatePipelineStatusService {
//	private final PipelineStatusMapper pipelineStatusMapper;
//	
//	public void changePipelineStatus(String pipelineId) {
//		
//		if(pipelineStatusMapper.areAllFilterConsumersTrue(pipelineId) == true) {
//			 pipelineStatusMapper.updatePipelineStatus(pipelineId, true);
//		}else {
//			 pipelineStatusMapper.updatePipelineStatus(pipelineId, false);
//		}
//		
//	}
//	
//	public void testtt(String pipelineId) {
//
//			 pipelineStatusMapper.updatePipelineStatus(pipelineId, false);
//		
//	}
//
//}
