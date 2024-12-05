package com.wnsud9771.service.dashboard;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.dashboard.barchart.BarchartDTO;
import com.wnsud9771.dto.dashboard.barchart.BarchartTimeDTO;
import com.wnsud9771.dto.dashboard.piechart.PiechartDTO;
import com.wnsud9771.dto.dashboard.piechart.PiechartTimeDTO;
import com.wnsud9771.dto.dashboard.priceboard.PriceboardDTO;
import com.wnsud9771.dto.dashboard.priceboard.PriceboardTimeDTO;
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
		List<Barchart> barcharts = barchartRepository.findAllByPipelinesId(id);
		if (barcharts == null || barcharts.isEmpty()) {
			asw.setBarchartDTOs(new ArrayList<>());
			return asw;
		}

		List<BarchartDTO> dtos = barchartRepository.findAllByPipelinesId(id).stream().map(this::converttobarchartDTO)
				.collect(Collectors.toList());

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

	public PiechartTimeDTO finddbpiechart(Long id) {

		Piechart entity = piechartRepository.findAllByPipelinesId(id);
		if (entity == null) {
			PiechartTimeDTO dto = new PiechartTimeDTO();
			dto.setPiechartDTO(new PiechartDTO());
			return dto;
		}

		return converttopiechartDTO(entity);
	}

	private PiechartTimeDTO converttopiechartDTO(Piechart entity) {
		PiechartTimeDTO dto = new PiechartTimeDTO();
		PiechartDTO pdto = new PiechartDTO();
		pdto.setSuccess(entity.getSuccess());
		pdto.setFailure(entity.getFailure());

		dto.setPiechartDTO(pdto);
		dto.setTimestamp(entity.getTimestamp());
		return dto;
	}

	public PriceboardTimeDTO finddbpriceboard(Long id) {
		Priceboard entity = priceboardRepository.findAllByPipelinesId(id);
		if (entity == null) {
			PriceboardTimeDTO dto = new PriceboardTimeDTO();
			dto.setPriceboardDTO(new PriceboardDTO());
			return dto;
		}
		return converttopriceboardDTO(entity);
	}

	private PriceboardTimeDTO converttopriceboardDTO(Priceboard entity) {
		PriceboardTimeDTO tdto = new PriceboardTimeDTO();
		PriceboardDTO dto = new PriceboardDTO();

		dto.setAverageValue(entity.getAverageValue());
		dto.setMaxValue(entity.getMaxValue());
		dto.setMinValue(entity.getMinValue());

		tdto.setPriceboardDTO(dto);
		tdto.setTimestamp(entity.getTimestamp());
		return tdto;
	}

	public TreemapTimeDTO finddbtreemap(Long id) {
		TreemapTimeDTO asw = new TreemapTimeDTO();
		List<Treemap> treemaps = treemapRepository.findAllByPipelinesId(id);
		if (treemaps == null || treemaps.isEmpty()) {
			asw.setTreemaps(new ArrayList<>());
			return asw;
		}

		List<TreemapDTO> dtos = treemapRepository.findAllByPipelinesId(id).stream().map(this::converttotreemapDTO)
				.collect(Collectors.toList());
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
