package com.wnsud9771.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.wnsud9771.dto.dashboard.pageview.DateAndCountDTO;
import com.wnsud9771.dto.dashboard.pageview.OnlyTimeDTO;
import com.wnsud9771.dto.dashboard.pageview.TimeRangeAndCountDTO;
import com.wnsud9771.dto.dashboard.pageview.VisitDayAndCountDTO;
import com.wnsud9771.dto.dashboard.productclick.DataAndCountDTO;

@Mapper
public interface FilteringDataMapper {
//	@Select("SELECT data, COUNT(*) as count " + "FROM filtering_data " + "WHERE pipelines_id = #{pipelineId} "
//			+ "GROUP BY data " + "ORDER BY count DESC")
//	List<DataAndCountDTO> findDataCountByPipelineId(Long pipelineId);

	// 상품클릭 시나리오 - top 5뽑기 - 중복제거x
	@Select("SELECT data, COUNT(*) as count " + "FROM filtering_data " + "WHERE pipelines_id = #{pipelineId} "
			+ "GROUP BY data " + "ORDER BY count DESC " + "LIMIT 5")
	List<DataAndCountDTO> findTop5DataCountByPipelineId(Long pipelineId);

	// 상품클릭 시나리오 - top 5뽑기 - 중복제거x
	@Select("SELECT data, COUNT(*) as count " + "FROM distinct_data " + "WHERE pipelines_id = #{pipelineId} "
			+ "GROUP BY data " + "ORDER BY count DESC " + "LIMIT 5")
	List<DataAndCountDTO> finddistinctTop5DataCountByPipelineId(Long pipelineId);

	// 상품 클릭 시나리오 - 브랜드 추출 - 중복제거 x
	@Select("SELECT data, COUNT(*) as count " + "FROM filtering_data " + "WHERE pipelines_id = #{pipelineId} "
			+ "GROUP BY data " + "ORDER BY count DESC ")
	List<DataAndCountDTO> findAllDataCountByPipelineId(Long pipelineId);

	// 상품 클릭 시나리오 - 브랜드 추출 - 중복제거 O
	@Select("SELECT data, COUNT(*) as count " + "FROM distinct_data " + "WHERE pipelines_id = #{pipelineId} "
			+ "GROUP BY 	 " + "ORDER BY count DESC ")
	List<DataAndCountDTO> findAllDatadistinctCountByPipelineId(Long pipelineId);

	// 20달러 시나리오 - 성공수 실패수 추출 - 중복제거 x
	@Select("SELECT COUNT(*) FROM filtering_data WHERE pipelines_id = #{pipelineId}")
	Long succountByPipelineId(Long pipelineId);
	@Select("SELECT COUNT(*) FROM fail_filtering_data WHERE pipelines_id = #{pipelineId}")
	Long failcountByPipelineId(Long pipelineId);
	
	//20달러 시나리오 - 최대 금액, 최소금액 , 평균금액 - 중복제거 x
	@Select("SELECT AVG(CAST(data AS SIGNED)) FROM filtering_data WHERE pipelines_id = #{pipelineId} AND CAST(data AS SIGNED) > 0")
	Long getAverageDataByPipelineId(Long pipelineId);

	@Select("SELECT MIN(CAST(data AS SIGNED)) FROM filtering_data WHERE pipelines_id = #{pipelineId} AND CAST(data AS SIGNED) > 0")
	Long getMinDataByPipelineId(Long pipelineId);

	@Select("SELECT MAX(CAST(data AS SIGNED)) FROM filtering_data WHERE pipelines_id = #{pipelineId} AND CAST(data AS SIGNED) > 0")
	Long getMaxDataByPipelineId(Long pipelineId);
	

//----------------------------------------------page view-----------------------------------------------------------------------------

	// 날짜별 방문 수 countByDate 재방문 전부포함.
	@Select("SELECT " +
	        "DATE_FORMAT(timestamp, '%Y-%m-%d') as date, " +
	        "COUNT(DISTINCT data) as count " +
	        "FROM filtering_data " +
	        "WHERE pipelines_id = #{pipelineId} " +
	        "GROUP BY DATE_FORMAT(timestamp, '%Y-%m-%d') " +
	        "ORDER BY date")
	List<DateAndCountDTO> countByDate(Long pipelineId);	

	//

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
	@Select("SELECT " +
	        "DATE_FORMAT(timestamp, '%W') as day, " +
	        "COUNT(DISTINCT data) as count " +
	        "FROM filtering_data " +
	        "WHERE pipelines_id = #{pipelineId} " +
	        "GROUP BY DATE_FORMAT(timestamp, '%W') " +
	        "ORDER BY FIELD(day, 'Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday', 'Sunday')")
	List<VisitDayAndCountDTO> countByDay(Long pipelineid);

//-----------------------------------------------------------------------------------------------------------------------------
}
