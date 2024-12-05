package com.wnsud9771.service.dashboard;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wnsud9771.dto.dashboard.piechart.PiechartDTO;
import com.wnsud9771.dto.dashboard.priceboard.PriceboardDTO;
import com.wnsud9771.dto.dashboard.productclick.BrandAndCountDTO;
import com.wnsud9771.dto.dashboard.productclick.DataAndCountDTO;
import com.wnsud9771.entity.dashboard.GraphPipelinesConnect;
import com.wnsud9771.entity.dashboard.graph.Barchart;
import com.wnsud9771.entity.dashboard.graph.Piechart;
import com.wnsud9771.entity.dashboard.graph.Priceboard;
import com.wnsud9771.entity.dashboard.graph.Treemap;
import com.wnsud9771.reoisitory.dashboard.BarchartRepository;
import com.wnsud9771.reoisitory.dashboard.PiechartRepository;
import com.wnsud9771.reoisitory.dashboard.PriceboardRepository;
import com.wnsud9771.reoisitory.dashboard.TreemapRepository;
import com.wnsud9771.reoisitory.pipeline.GraphPipelinesConnectRepository;
import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SubmitGraphsdataService {
	private final GraphPipelinesConnectRepository graphPipelinesConnectRepository;
	private final PipelinesRepository pipelinesRepository;
	private final GraphService graphService;
	private final BarchartRepository barchartRepository;
	private final PiechartRepository piechartRepository;
	private final PriceboardRepository priceboardRepository;
	private final TreemapRepository treemapRepository;
	
	@Transactional
	public void findgraphstyleandsubmit(String pipelineid) {

		Long pid = pipelinesRepository.findByPipelineId(pipelineid).getId();
		Long distinctcode = pipelinesRepository.findByPipelineId(pipelineid).getDistinctCode();

		if (distinctcode == null) {

			List<String> graphstyles = new ArrayList<>();
			List<GraphPipelinesConnect> connects = graphPipelinesConnectRepository.findGraphListByPipelinesId(pid);

			for (GraphPipelinesConnect connect : connects) {
				graphstyles.add(connect.getGraphList().getGraphName());
			}

			for (String graphstyle : graphstyles) {

				if (graphstyle.equals("Barchart")) {
					if (!barchartRepository.findAllByPipelinesId(pid).isEmpty()) {
						barchartRepository.deleteByPipelinesId(pid);
					}

					List<DataAndCountDTO> dtos = graphService.top5graphconvert(pid);

					for (DataAndCountDTO dto : dtos) {
						Barchart entity = new Barchart();
						entity.setData(dto.getData());
						entity.setCount(dto.getCount());
						entity.setPipelinesId(pid);
						entity.setTimestamp(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

						barchartRepository.save(entity);
					}

				} else if (graphstyle.equals("Piechart")) {
					if (piechartRepository.findAllByPipelinesId(pid) != null) {
						piechartRepository.deleteByPipelinesId(pid);
					}
					
					 PiechartDTO dto =graphService.sucorfaildata(pid);
					 Piechart entity = new Piechart();
					 entity.setSuccess(dto.getSuccess());
					 entity.setFailure(dto.getFailure());
					 entity.setPipelinesId(pid);
					 entity.setTimestamp(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
					 
					 piechartRepository.save(entity);
					
					
				} else if (graphstyle.equals("PriceBoard")) {
					if (priceboardRepository.findAllByPipelinesId(pid) != null) {
						priceboardRepository.deleteByPipelinesId(pid);
					}
					
					PriceboardDTO dto = graphService.avgmaxmin(pid);
					Priceboard entity = new Priceboard();
					entity.setAverageValue(dto.getAverageValue());
					entity.setMaxValue(dto.getMaxValue());
					entity.setMinValue(dto.getMinValue());
					entity.setTimestamp(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
					
					priceboardRepository.save(entity);

				} else if (graphstyle.equals("Treemap")) {
					if (!treemapRepository.findAllByPipelinesId(pid).isEmpty()) {
						treemapRepository.deleteByPipelinesId(pid);
					}

					List<BrandAndCountDTO> dtos = graphService.brandandcount(pid);

					for (BrandAndCountDTO dto : dtos) {
						Treemap entity = new Treemap();
						entity.setBrandname(dto.getBrandname());
						entity.setCount(dto.getCount());
						entity.setTimestamp(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

						treemapRepository.save(entity);
					}

				}
			}

		}
		
		else if (distinctcode == 3L) {
			List<String> graphstyles = new ArrayList<>();
			List<GraphPipelinesConnect> connects = graphPipelinesConnectRepository.findGraphListByPipelinesId(pid);

			for (GraphPipelinesConnect connect : connects) {
				graphstyles.add(connect.getGraphList().getGraphName());
			}

			for (String graphstyle : graphstyles) {

				if (graphstyle.equals("Barchart")) {
					if (!barchartRepository.findAllByPipelinesId(pid).isEmpty()) {
						barchartRepository.deleteByPipelinesId(pid);
					}

					List<DataAndCountDTO> dtos = graphService.top5graphconvertdistinct(pid);

					for (DataAndCountDTO dto : dtos) {
						Barchart entity = new Barchart();
						entity.setData(dto.getData());
						entity.setCount(dto.getCount());
						entity.setPipelinesId(pid);
						entity.setTimestamp(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

						barchartRepository.save(entity);
					}

				} else if (graphstyle.equals("Piechart")) {

				} else if (graphstyle.equals("PriceBoard")) {

				} else if (graphstyle.equals("Treemap")) {
					if (!treemapRepository.findAllByPipelinesId(pid).isEmpty()) {
						treemapRepository.deleteByPipelinesId(pid);
					}

					List<BrandAndCountDTO> dtos = graphService.brandandcountdistinct(pid);

					for (BrandAndCountDTO dto : dtos) {
						Treemap entity = new Treemap();
						entity.setBrandname(dto.getBrandname());
						entity.setCount(dto.getCount());
						entity.setTimestamp(LocalDateTime.now(ZoneId.of("Asia/Seoul")));

						treemapRepository.save(entity);
					}

				}
			}
			
			
		}
	}
}
