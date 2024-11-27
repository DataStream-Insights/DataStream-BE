//package com.wnsud9771.service.test;
//
//import org.springframework.stereotype.Service;
//
//import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;
//
//import jakarta.annotation.PostConstruct;
//import jakarta.transaction.Transactional;
//import lombok.RequiredArgsConstructor;
//
//@Service
//@RequiredArgsConstructor
//public class PostService {
//	 private final PipelinesRepository pipelinesRepository;
//
//
//	    @PostConstruct
//	    @Transactional
//	    public void init() {
//	        try {
//	            pipelinesRepository.deleteById(7L);
//	            System.out.println("Pipeline 삭제 완료");
//	        } catch (Exception e) {
//	            System.out.println("Pipeline 삭제 중 에러 발생: " + e.getMessage());
//	        }
//	    }
//}
