package com.wnsud9771.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wnsud9771.dto.dashboard.top5.DataAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.DateAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.OnlyTimeDTO;
import com.wnsud9771.dto.dashboard.visits.TimeRangeAndCountDTO;
import com.wnsud9771.dto.dashboard.visits.VisitDayAndCountDTO;

@Mapper
public interface FilteringDataMaepper {
//	@Select("SELECT data, COUNT(*) as count " + "FROM filtering_data " + "WHERE pipelines_id = #{pipelineId} "
//			+ "GROUP BY data " + "ORDER BY count DESC")
//	List<DataAndCountDTO> findDataCountByPipelineId(Long pipelineId);
	
	@Select("SELECT data, COUNT(*) as count " + "FROM filtering_data " + "WHERE pipelines_id = #{pipelineId} "
			+ "GROUP BY data " + "ORDER BY count DESC " + "LIMIT 5")
	List<DataAndCountDTO> findDataCountByPipelineId(Long pipelineId);

	// 날짜별 방문 수 countByDate
	@Select("SELECT " + "DATE_FORMAT(timestamp, '%Y-%m-%d') as date, " + "COUNT(*) as count " + "FROM filtering_data "
			+ "WHERE pipelines_id = #{pipelineId} " + "GROUP BY DATE_FORMAT(timestamp, '%Y-%m-%d') " + "ORDER BY date")
	List<DateAndCountDTO> countByDate(Long pipelineId);

	// 특정 날짜 시간대별로 countByTimeRangeForDate
	@Select("SELECT " + "DATE_FORMAT(timestamp, '%H:00-%H:59') as timeRange, " + "COUNT(*) as count "
			+ "FROM filtering_data " + "WHERE pipelines_id = #{pipelineId} " + "AND DATE(timestamp) = #{date} "
			+ "GROUP BY DATE_FORMAT(timestamp, '%H') " + "ORDER BY timeRange")
	List<TimeRangeAndCountDTO> countByTimeRangeForDate(Long pipelineId, String date);

	// 날짜 상관없이 모든 날짜 시간대별 getTimeRangeCount
	@Select("SELECT DATE_FORMAT(timestamp, '%H:00-%H:59') as timeRange, COUNT(*) as count " + "FROM filtering_data "
			+ "WHERE pipelines_id = #{pipelineId} " + "GROUP BY DATE_FORMAT(timestamp, '%H') "
			+ "ORDER BY DATE_FORMAT(timestamp, '%H')")
	List<OnlyTimeDTO> getTimeRangeCount(Long pipelineId);

	// 요일별( 수요일,목요일, 금요일) countByDay
	@Select("SELECT DATE_FORMAT(timestamp, '%W') as day, COUNT(*) as count " + "FROM filtering_data "
			+ "WHERE pipelines_id = #{pipelineId} " + "GROUP BY DATE_FORMAT(timestamp, '%W') "
			+ "ORDER BY FIELD(day, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')")
	List<VisitDayAndCountDTO> countByDay(Long pipelineid);
}
