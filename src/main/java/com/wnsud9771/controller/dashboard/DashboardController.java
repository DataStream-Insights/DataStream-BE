package com.wnsud9771.controller.dashboard;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wnsud9771.dto.dashboard.ProcessDTO;
import com.wnsud9771.dto.dashboard.pageview.DateAndCountDTO;
import com.wnsud9771.dto.dashboard.pageview.OnlyTimeDTO;
import com.wnsud9771.dto.dashboard.pageview.TimeRangeAndCountDTO;
import com.wnsud9771.dto.dashboard.pageview.VisitDayAndCountDTO;
import com.wnsud9771.dto.dashboard.productclick.BrandAndCountDTO;
import com.wnsud9771.dto.dashboard.productclick.DataAndCountDTO;
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
//	
//	@Operation(summary = "그래프 유형 목록 ", description = ".")
//	@GetMapping("/processes")
//	public ResponseEntity<List<ProcessDTO>> findProcesses() {
//		return ResponseEntity.ok(dashboardService.getprocessList());
//		
//	}
//	
	// 상품 클릭 시나리오 - top5
	@Operation(summary = "인기상품 top5 그래프 데이터 ", description = "프로세스 기본 id 경로로 get")
	@GetMapping("/Barchart/{id}")
	public ResponseEntity<List<DataAndCountDTO>> top5Graph(@PathVariable Long id) {
		
		return ResponseEntity.ok(graphService.top5graphconvert(id));
		
	}
	
	//상품 클릭 시나리오 - 브랜드 추출
	@Operation(summary = "브랜드 추출 api ", description = "프로세스 기본 id 경로로 get")
	@GetMapping("/Treemap/{id}")
	public ResponseEntity<List<BrandAndCountDTO>> brandcount(@PathVariable Long id) {
		
		return ResponseEntity.ok(graphService.brandandcount(id));
		
	}
	
	// 날짜 상관없이 모든 날짜 시간대별 getTimeRangeCount
	@Operation(summary = "날짜 상관없이 모든 날짜 시간대별 getTimeRangeCount ", description = "날짜, 그날짜의 방문수 이렇게 보내짐.  2024-11-26일날 3번방문 2024-11-28에 25번방문 이러면 데이터형식에 맞춰서")
	@GetMapping("/processes/dategraph/datetimerrangeandcount")
	public ResponseEntity<List<OnlyTimeDTO>> alltimerangecount() {
		Long id = 30L; //프로세스 만들면 그 프로세스 id 넣기~!
		return ResponseEntity.ok(graphService. alltimerangeandcount(id));
		
	}
	
	
	// 특정 날짜 시간대별로 countByTimeRangeForDate
	@Operation(summary = "특정 요일 시간대별 방문자수 데이터 ", description = "|요일 형식 YYYY-MM-DD| *그냥 28 이렇게넣으면 몇월 몇년이든 28일 데이터가 다뽑아짐, 특정 날을 원하면 저렇게")
	@GetMapping("/processes/dategraph/datetimerrangeandcount/{date}")
	public ResponseEntity<List<TimeRangeAndCountDTO>> datetimerrangeandcountcon(@PathVariable String date) {
		log.info("date = ::: {} {}",date);
		Long id = 30L; //프로세스 만들면 그 프로세스 id 넣기~!
		return ResponseEntity.ok(graphService.datetimerangeandcount(id,date));
		
	}
	
	// 날짜별 방문 수 countByDate
	@Operation(summary = "날짜별 방문 수 countByDate", description = "그냥 id만 넣으면 날짜별 방문 수 보여주기 몇년몇월며칠에 몇번 방문 이런식으로")
	@GetMapping("/processes/dategraph/countByDate")
	public ResponseEntity<List<DateAndCountDTO>> selectcountByDatecon() {
		Long id = 30L; //프로세스 만들면 그 프로세스 id 넣기~!
		return ResponseEntity.ok(graphService.selectcountByDate(id));
		
	}
	
	//요일별( 수요일,목요일, 금요일) countByDay
	@Operation(summary = "요일별( 수요일,목요일, 금요일) countByDay", description = "그냥 수요일,목요일처럼 요일별 방문 수 보여주기")
	@GetMapping("/processes/dategraph/dayvisit")
	public ResponseEntity<List<VisitDayAndCountDTO>> datetimerangeandcountcon() {
		Long id = 30L; //프로세스 만들면 그 프로세스 id 넣기~!
		return ResponseEntity.ok(graphService.dayvisitcount(id));
		
	}
	
}
