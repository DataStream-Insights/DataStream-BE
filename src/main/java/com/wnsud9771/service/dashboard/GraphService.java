package com.wnsud9771.service.dashboard;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.dashboard.pageview.DateAndCountDTO;
import com.wnsud9771.dto.dashboard.pageview.OnlyTimeDTO;
import com.wnsud9771.dto.dashboard.pageview.TimeRangeAndCountDTO;
import com.wnsud9771.dto.dashboard.pageview.VisitDayAndCountDTO;
import com.wnsud9771.dto.dashboard.piechart.PiechartDTO;
import com.wnsud9771.dto.dashboard.priceboard.PriceboardDTO;
import com.wnsud9771.dto.dashboard.productclick.BrandAndCountDTO;
import com.wnsud9771.dto.dashboard.productclick.DataAndCountDTO;
import com.wnsud9771.mapper.FilteringDataMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphService {
	private final FilteringDataMapper filteringDataMapper;

	// 상품클릭 시나리오 - top5 조회 아이템과 카운트만 뽑기 - 중복제거x
	public List<DataAndCountDTO> top5graphconvert(Long id) {

		return filteringDataMapper.findTop5DataCountByPipelineId(id);

	}

	// 상품클릭 시나리오 - top5 조회 아이템과 카운트만 뽑기 - 중복제거O
	public List<DataAndCountDTO> top5graphconvertdistinct(Long id) {

		return filteringDataMapper.finddistinctTop5DataCountByPipelineId(id);

	}

	// 상품클릭 시나리오 - 브랜드 추출 - 중복제거x
	public List<BrandAndCountDTO> brandandcount(Long id) {
		List<BrandAndCountDTO> setdtos = new ArrayList<>();
		List<DataAndCountDTO> dtos = filteringDataMapper.findAllDataCountByPipelineId(id);

		for (DataAndCountDTO dto : dtos) {
			if (dto.getData().startsWith("http://14.63.178.40:8080/CerealMarket-1.1.5/")) {
				BrandAndCountDTO setdto = new BrandAndCountDTO();
				String temp = dto.getData().substring("http://14.63.178.40:8080/CerealMarket-1.1.5/".length());
				if (temp.equals("cheeriosproduct.jsp"))
					setdto.setBrandname("cheerios");
				else if (temp.equals("cinnamonproduct.jsp"))
					setdto.setBrandname("cinnamon");
				else if (temp.equals("kelloggproduct.jsp"))
					setdto.setBrandname("kellogg");
				else if (temp.equals("luckycharmsproduct.jsp"))
					setdto.setBrandname("luckycharms");
				else
					setdto.setBrandname(null);

				setdto.setCount(dto.getCount());
				setdtos.add(setdto);
			}

		}

		return setdtos;
	}

	// 상품클릭 시나리오 - 브랜드 추출 - 중복제거)
	public List<BrandAndCountDTO> brandandcountdistinct(Long id) {
		List<BrandAndCountDTO> setdtos = new ArrayList<>();
		BrandAndCountDTO setdto = new BrandAndCountDTO();
		List<DataAndCountDTO> dtos = filteringDataMapper.findAllDatadistinctCountByPipelineId(id);

		for (DataAndCountDTO dto : dtos) {
			if (dto.getData().startsWith("http://14.63.178.40:8080/CerealMarket-1.1.5/")) {
				String temp = new String();
				temp = dto.getData().substring("http://14.63.178.40:8080/CerealMarket-1.1.5/".length());
				if (temp.equals("cheeriosproduct.jsp"))
					setdto.setBrandname("cheerios");
				else if (temp.equals("cinnamonproduct.jsp"))
					setdto.setBrandname("cinnamon");
				else if (temp.equals("kelloggproduct.jsp"))
					setdto.setBrandname("kellogg");
				else if (temp.equals("luckycharmsproduct.jsp"))
					setdto.setBrandname("luckycharms");
				else
					continue;

				setdto.setCount(dto.getCount());
			}

		}

		return setdtos;
	}

	// 20달러 시나리오 - 성공수, 실패수 반환하는거 - 중복제거 x
	public PiechartDTO sucorfaildata(Long id) {
		PiechartDTO dto = new PiechartDTO();

		Long successCount = filteringDataMapper.succountByPipelineId(id);
		Long failCount = filteringDataMapper.failcountByPipelineId(id);

		dto.setSuccess(successCount);
		dto.setFailure(failCount);

		return dto;
	}

	// 20달러 시나리오 - 평균금액수, 최고 금액, 최소 금액 반환
	public PriceboardDTO avgmaxmin(Long id) {
		PriceboardDTO dto = new PriceboardDTO();

		Long avgvalue = filteringDataMapper.getAverageDataByPipelineId(id);
		Long maxvalue = filteringDataMapper.getMaxDataByPipelineId(id);
		Long minvalue = filteringDataMapper.getMinDataByPipelineId(id);

		dto.setAverageValue(avgvalue);
		dto.setMaxValue(maxvalue);
		dto.setMinValue(minvalue);

		return dto;

	}

	// --------------------------------------------------------------page
	// view--------------------------------------------------

	// 날짜별 방문 수 countByDate
	public List<DateAndCountDTO> selectcountByDate(Long id) {
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

	// 요일별( 수요일,목요일, 금요일) countByDay
	public List<VisitDayAndCountDTO> dayvisitcount(Long id) {
		return filteringDataMapper.countByDay(id);
	}

//------------------------------------------------------------------------------------------------------------------------
}
