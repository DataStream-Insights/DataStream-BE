package com.wnsud9771.service.dashboard;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.dashboard.top5.DataAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.DateAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.OnlyTimeDTO;
import com.wnsud9771.dto.dashboard.visits.TimeRangeAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.VisitDayAndCountDTO;
import com.wnsud9771.mapper.FilteringDataMaepper;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphService {
	private final FilteringDataMaepper filteringDataMapper;

	// top5 조회 아이템과 카운트만 뽑기
	public List<DataAndCountDTO> top5graphconvert(Long id) {

		return filteringDataMapper.findDataCountByPipelineId(id);

	}

	// 날짜별 방문 수 countByDate
	public List<DateAndCountDTO> selectcountByDate(Long id){
		return filteringDataMapper.countByDate(id);
	}
	
	
	
	
	// 특정 날짜 시간대별로 countByTimeRangeForDate
	public List<TimeRangeAndCountDTO> datetimerangeandcount(Long id, String date) {

		return filteringDataMapper.countByTimeRangeForDate(id, date);
	}
	
//	@PostConstruct
//	public void init() {
//		System.out.println (datetimerangeandcount(26L, "2024-11-28") );
//	}
	
	
	// 날짜 상관없이 모든 날짜 시간대별 getTimeRangeCount

	public List<OnlyTimeDTO> alltimerangeandcount(Long id) {

		return filteringDataMapper.getTimeRangeCount(id);
	}
	
	
	
	//요일별( 수요일,목요일, 금요일) countByDay
	public List<VisitDayAndCountDTO> dayvisitcount(Long id){
		return filteringDataMapper.countByDay(id);
	}
}
