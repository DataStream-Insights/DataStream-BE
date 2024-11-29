package com.wnsud9771.controller.dashboard;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.dashboard.ProcessDTO;
import com.wnsud9771.dto.dashboard.top5.DataAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.DateAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.OnlyTimeDTO;
import com.wnsud9771.dto.dashboard.visits.TimeRangeAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.VisitDayAndCountDTO;
import com.wnsud9771.service.dashboard.DashboardService;
import com.wnsud9771.service.dashboard.GraphService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Slf4j
public class DashboardController {
	private final DashboardService dashboardService;
	private final GraphService graphService;
	
	@Operation(summary = "프로세스 목록보기 ", description = ".")
	@GetMapping("/processes")
	public ResponseEntity<List<ProcessDTO>> findProcesses() {
		return ResponseEntity.ok(dashboardService.getprocessList());
		
	}
	
	@Operation(summary = "인기상품 top5 그래프 데이터 ", description = "프로세스 기본 id 경로로 get")
	@GetMapping("/processes/top5/{id}")
	public ResponseEntity<List<DataAndCountDTO>> top5Graph(@PathVariable Long id) {
		
		return ResponseEntity.ok(graphService.top5graphconvert(id));
		
	}
	
	// 날짜 상관없이 모든 날짜 시간대별 getTimeRangeCount
	@Operation(summary = "날짜 상관없이 모든 날짜 시간대별 getTimeRangeCount ", description = "날짜, 그날짜의 방문수 이렇게 보내짐.  2024-11-26일날 3번방문 2024-11-28에 25번방문 이러면 데이터형식에 맞춰서")
	@GetMapping("/processes/{id}/datetimerrangeandcount")
	public ResponseEntity<List<OnlyTimeDTO>> alltimerangecount(@PathVariable Long id) {
		
		return ResponseEntity.ok(graphService. alltimerangeandcount(id));
		
	}
	
	
	// 특정 날짜 시간대별로 countByTimeRangeForDate
	@Operation(summary = "특정 요일 시간대별 방문자수 데이터 ", description = "|요일 형식 YYYY-MM-DD| *그냥 28 이렇게넣으면 몇월 몇년이든 28일 데이터가 다뽑아짐, 특정 날을 원하면 저렇게")
	@GetMapping("/processes/{id}/datetimerrangeandcount/{date}")
	public ResponseEntity<List<TimeRangeAndCountDTO>> datetimerrangeandcountcon(@PathVariable Long id, @PathVariable String date) {
		log.info("date = ::: {} {}",date);
		return ResponseEntity.ok(graphService.datetimerangeandcount(id,date));
		
	}
	
	// 날짜별 방문 수 countByDate
	@Operation(summary = "날짜별 방문 수 countByDate", description = "그냥 id만 넣으면 날짜별 방문 수 보여주기 몇년몇월며칠에 몇번 방문 이런식으로")
	@GetMapping("/processes/{id}/countByDate")
	public ResponseEntity<List<DateAndCountDTO>> selectcountByDatecon(@PathVariable Long id) {
		
		return ResponseEntity.ok(graphService.selectcountByDate(id));
		
	}
	
	//요일별( 수요일,목요일, 금요일) countByDay
	@Operation(summary = "요일별( 수요일,목요일, 금요일) countByDay", description = "그냥 수요일,목요일처럼 요일별 방문 수 보여주기")
	@GetMapping("/processes/{id}/dayvisit")
	public ResponseEntity<List<VisitDayAndCountDTO>> datetimerangeandcountcon(@PathVariable Long id) {
		
		return ResponseEntity.ok(graphService.dayvisitcount(id));
		
	}
	
}
