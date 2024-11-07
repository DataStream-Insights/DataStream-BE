package com.wnsud9771.service.sendkafka;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.wnsud9771.dto.receivelog.SendLogDTO;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class FormatingSendService {
	
	//비동기 처리떄문에 임시적으로 놔두고 비동기처리로 바꿈
//	 private final RestTemplate restTemplate;
//	 private static final String RECEIVE_LOG_URL = "http://localhost:8084/logs/formating"; //kafka2로 전송
//	 
//	 public SendLogDTO sendLogData(SendLogDTO SendlogDTO) {
//		 	log.info("send dto data {} : ", SendlogDTO.getLog_data());
//	        return restTemplate.postForObject(RECEIVE_LOG_URL, SendlogDTO, SendLogDTO.class);
//	 }
	
	
	//프로젝트 실행순서때문에 임시적으로 비동기 처리로 보내는
	private final RestTemplate restTemplate;
    private static final String RECEIVE_LOG_URL = "http://localhost:8084/logs/formating";
    
    // 재시도 대기 큐
    private final BlockingQueue<SendLogDTO> pendingMessages = new LinkedBlockingQueue<>();
    
    // 재시도 스케줄러
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    
    // 서비스 시작 시 재시도 처리 시작
    @PostConstruct
    public void init() {
        startRetryProcess();
    }
    
    // 서비스 종료 시 정리
    @PreDestroy
    public void cleanup() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }

    public SendLogDTO sendLogData(SendLogDTO sendLogDTO) {
        try {
            return attemptSend(sendLogDTO);
        } catch (Exception e) {
            log.warn("Failed to send log data, queuing for retry: {}", e.getMessage());
            pendingMessages.offer(sendLogDTO);
            return sendLogDTO;
        }
    }

    private SendLogDTO attemptSend(SendLogDTO sendLogDTO) {
        try {
            log.info("Attempting to send dto data: {}", sendLogDTO.getContents());
            return restTemplate.postForObject(RECEIVE_LOG_URL, sendLogDTO, SendLogDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to send message", e);
        }
    }

    private void startRetryProcess() {
        scheduler.scheduleWithFixedDelay(() -> {
            if (!pendingMessages.isEmpty()) {
                log.info("Attempting to resend {} pending messages", pendingMessages.size());
                
                List<SendLogDTO> batch = new ArrayList<>();
                pendingMessages.drainTo(batch);
                
                for (SendLogDTO msg : batch) {
                    try {
                        attemptSend(msg);
                        log.info("Successfully resent message");
                    } catch (Exception e) {
                        log.warn("Retry failed, re-queuing message: {}", e.getMessage());
                        pendingMessages.offer(msg);
                    }
                }
            }
        }, 0, 1, TimeUnit.MINUTES);
    }
}
