package com.wnsud9771.service.dashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.dashboard.barchart.BarchartDTO;
import com.wnsud9771.dto.dashboard.barchart.BarchartTimeDTO;
import com.wnsud9771.entity.dashboard.graph.Barchart;
import com.wnsud9771.reoisitory.dashboard.BarchartRepository;
import com.wnsud9771.reoisitory.dashboard.PiechartRepository;
import com.wnsud9771.reoisitory.dashboard.PriceboardRepository;
import com.wnsud9771.reoisitory.dashboard.TreemapRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GraphDBService {
	private final BarchartRepository barchartRepository;
	private final PiechartRepository piechartRepository;
	private final PriceboardRepository priceboardRepository;
	private final TreemapRepository treemapRepository;
	
	
	
	public BarchartTimeDTO finddbbarchart(Long id) {
		BarchartTimeDTO asw = new BarchartTimeDTO();
		List<BarchartDTO> dtos = barchartRepository.findAllByPipelinesId(id).stream().map(this::converttoDTO).collect(Collectors.toList());
		asw.setTimestamp(barchartRepository.findAllByPipelinesId(id).getFirst().getTimestamp());
		asw.setBarchartDTOs(dtos);
		return asw;
	}
	
	private BarchartDTO converttoDTO(Barchart entity) {
		BarchartDTO dto = new BarchartDTO();
		dto.setCount(entity.getCount());
		dto.setData(entity.getData());
		return dto;
	}
}
