package com.wnsud9771.service.dashboard;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.dashboard.barchart.BarchartDTO;
import com.wnsud9771.dto.dashboard.barchart.BarchartTimeDTO;
import com.wnsud9771.dto.dashboard.piechart.PiechartDTO;
import com.wnsud9771.dto.dashboard.priceboard.PriceboardDTO;
import com.wnsud9771.dto.dashboard.treemap.TreemapDTO;
import com.wnsud9771.dto.dashboard.treemap.TreemapTimeDTO;
import com.wnsud9771.entity.dashboard.graph.Barchart;
import com.wnsud9771.entity.dashboard.graph.Piechart;
import com.wnsud9771.entity.dashboard.graph.Priceboard;
import com.wnsud9771.entity.dashboard.graph.Treemap;
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
		List<BarchartDTO> dtos = barchartRepository.findAllByPipelinesId(id).stream().map(this::converttobarchartDTO).collect(Collectors.toList());
		asw.setTimestamp(barchartRepository.findAllByPipelinesId(id).getLast().getTimestamp());
		asw.setBarchartDTOs(dtos);
		return asw;
	}
	
	private BarchartDTO converttobarchartDTO(Barchart entity) {
		BarchartDTO dto = new BarchartDTO();
		dto.setCount(entity.getCount());
		dto.setData(entity.getData());
		return dto;
	}
	
	public PiechartDTO finddbpiechart(Long id) {
		return converttopiechartDTO(piechartRepository.findAllByPipelinesId(id));
	}
	
	private PiechartDTO converttopiechartDTO(Piechart entity) {
		PiechartDTO dto = new PiechartDTO();
		dto.setSuccess(entity.getSuccess());
		dto.setFailure(entity.getFailure());
		dto.setTimestamp(entity.getTimestamp());
		return dto;
	}
	
	public PriceboardDTO finddbpriceboard(Long id) {
		return converttopriceboardDTO(priceboardRepository.findAllByPipelinesId(id));
	}
	
	private PriceboardDTO converttopriceboardDTO(Priceboard entity) {
		PriceboardDTO dto = new PriceboardDTO();
		dto.setAverageValue(entity.getAverageValue());
		dto.setMaxValue(entity.getMaxValue());
		dto.setMinValue(entity.getMinValue());
		dto.setTimestamp(entity.getTimestamp());
		return dto;
	}
	
	
	public TreemapTimeDTO finddbtreemap(Long id) {
		TreemapTimeDTO asw = new TreemapTimeDTO();
		List<TreemapDTO> dtos = treemapRepository.findAllByPipelinesId(id).stream().map(this::converttotreemapDTO).collect(Collectors.toList());
		asw.setTimestamp(treemapRepository.findAllByPipelinesId(id).getLast().getTimestamp());
		asw.setTreemaps(dtos);
		return asw;
	}
	
	private TreemapDTO converttotreemapDTO(Treemap entity) {
		TreemapDTO dto = new TreemapDTO();
		dto.setName(entity.getBrandname());
		dto.setValue(entity.getCount());
		return dto;
	}
}
