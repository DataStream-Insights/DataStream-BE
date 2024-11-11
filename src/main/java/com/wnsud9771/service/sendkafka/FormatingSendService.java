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

//	 private final RestTemplate restTemplate;
//	 private static final String RECEIVE_LOG_URL = "http://localhost:8084/logs/formating"; //kafka2로 전송
//	 
//	 public SendLogDTO sendLogData(SendLogDTO SendlogDTO) {
//		 	log.info("send dto data {} : ", SendlogDTO.getLog_data());
//	        return restTemplate.postForObject(RECEIVE_LOG_URL, SendlogDTO, SendLogDTO.class);
//	 }

	private final RestTemplate restTemplate;
	private static final String RECEIVE_LOG_URL = "http://localhost:8084/logs/formating";

	// 재시도 대기 큐
	private final BlockingQueue<RetryMessage> pendingMessages = new LinkedBlockingQueue<>();

	// 재시도 스케줄러
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

	// 최대 재시도 횟수
	private static final int MAX_RETRY_ATTEMPTS = 3;

	// 재시도 메시지를 추적하기 위한 내부 클래스
	private static class RetryMessage {
		final SendLogDTO message;
		int attempts;

		RetryMessage(SendLogDTO message) {
			this.message = message;
			this.attempts = 0;
		}
	}

	@PostConstruct
	public void init() {
		startRetryProcess();
	}

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
			pendingMessages.offer(new RetryMessage(sendLogDTO));
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

				List<RetryMessage> batch = new ArrayList<>();
				pendingMessages.drainTo(batch);

				for (RetryMessage retryMsg : batch) {
					// 최대 재시도 횟수를 초과하지 않은 경우에만 재시도
					if (retryMsg.attempts < MAX_RETRY_ATTEMPTS) {
						try {
							attemptSend(retryMsg.message);
							log.info("Successfully resent message after {} attempts", retryMsg.attempts + 1);
							// 성공적으로 전송되면 재시도 큐에 다시 추가하지 않음
						} catch (Exception e) {
							log.warn("Retry attempt {} failed for message: {}", retryMsg.attempts + 1, e.getMessage());
							retryMsg.attempts++;
							// 아직 최대 재시도 횟수에 도달하지 않았다면 다시 큐에 추가
							if (retryMsg.attempts < MAX_RETRY_ATTEMPTS) {
								pendingMessages.offer(retryMsg);
							} else {
								log.error("Message failed after maximum retry attempts ({}). Dropping message.",
										MAX_RETRY_ATTEMPTS);
							}
						}
					}
				}
			}
		}, 0, 20, TimeUnit.SECONDS);
	}
}