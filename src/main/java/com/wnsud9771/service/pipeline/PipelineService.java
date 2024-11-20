package com.wnsud9771.service.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.wnsud9771.dto.pipeline.ProcessStartDTO;
import com.wnsud9771.dto.pipeline.add.AddCampaignTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddFilterTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddFormatTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;
import com.wnsud9771.dto.pipeline.search.PipelinesDTO;
import com.wnsud9771.entity.pipelineentity.CampaignTopic;
import com.wnsud9771.entity.pipelineentity.FilterTopic;
import com.wnsud9771.entity.pipelineentity.FormatTopic;
import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.event.pipeline.PipelineStartEvent;
import com.wnsud9771.event.pipeline.PipelineStopEvent;
import com.wnsud9771.reoisitory.pipeline.CampaignTopicRepository;
import com.wnsud9771.reoisitory.pipeline.FilterTopicRepository;
import com.wnsud9771.reoisitory.pipeline.FormatTopicRepository;
import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PipelineService {
	private final PipelinesRepository pipelinesRepository;
	private final CampaignTopicRepository campaignTopicRepository;
	private final FormatTopicRepository formatTopicRepository;
	private final FilterTopicRepository filterTopicRepository;
	private final ApplicationEventPublisher eventPublisher;
	private final ConnectCFFService connectCFFService;
	
	//파이프라인 저장
	public AddPipelineDTO submitpipeline(AddPipelineDTO dto) {

		Pipelines pipeline = new Pipelines();
		pipeline.setPipelineId(dto.getPipelineId());
		pipeline.setName(dto.getPipelineName());
		pipeline = pipelinesRepository.save(pipeline);

		// CampaignTopic 저장+ pipeline 연결
		if (dto.getAddcampaignTopic() != null) {
			CampaignTopic campaignTopic = new CampaignTopic();
			campaignTopic.setCampaignId(dto.getAddcampaignTopic().getCampaignId());
			campaignTopic.setPipelines(pipeline);
			 campaignTopic = campaignTopicRepository.save(campaignTopic);

			// FormatTopic 저장 + pipeline 연결
			if (dto.getAddcampaignTopic().getAddFormatTopics() != null) {
				for (AddFormatTopicDTO addformatTopicdto : dto.getAddcampaignTopic().getAddFormatTopics()) {
					FormatTopic formatTopic = new FormatTopic();
					formatTopic.setCampaignId(dto.getAddcampaignTopic().getCampaignId());
					formatTopic.setFormatId(addformatTopicdto.getFormatId());
					formatTopic.setCampaignTopic(campaignTopic);
					formatTopic = formatTopicRepository.save(formatTopic);
					
					connectCFFService.connectCampaignAndFormat(dto.getAddcampaignTopic().getCampaignId(), addformatTopicdto.getFormatId());

					// 4. FilterTopic 저장
					if (addformatTopicdto.getAddFilterTopics() != null) {
						for (AddFilterTopicDTO filterTopicdto : addformatTopicdto.getAddFilterTopics()) {
							FilterTopic filterTopic = new FilterTopic();
							filterTopic.setFilterId(filterTopicdto.getFilterId());
							filterTopic.setFormatId(addformatTopicdto.getFormatId());
							filterTopic.setFormatTopic(formatTopic);
							filterTopicRepository.save(filterTopic);
							
							connectCFFService.connectFormatAndFilter(addformatTopicdto.getFormatId(), filterTopicdto.getFilterId());

						}
					}
				}
			}
		}

		return dto;
	}
	
	//파이프라인 목록 조회
	public List<PipelinesDTO> findpipelinelist() {
		return pipelinesRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}
	
	private PipelinesDTO convertToDTO(Pipelines entity) {
		PipelinesDTO dto = new PipelinesDTO();
		
		dto.setId(entity.getId());
		dto.setPipelineId(entity.getPipelineId());
		dto.setPipelinename(entity.getName());
		dto.setStatus(entity.isStatus());
		
		return dto;
	}
	
	public AddPipelineDTO findpipelinebykeyid(Long id) {
		AddPipelineDTO dto = new AddPipelineDTO();
		
	    Pipelines pipeline = pipelinesRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Pipeline not found with id: " + id));
	    
	    dto.setPipelineId(pipeline.getPipelineId());
	    dto.setPipelineName(pipeline.getName());
		
	    CampaignTopic campaignTopic = campaignTopicRepository.findByPipelines(pipeline);
	    if (campaignTopic != null) {
	        AddCampaignTopicDTO campaignTopicDTO = new AddCampaignTopicDTO();
	        campaignTopicDTO.setCampaignId(campaignTopic.getCampaignId());

	        // FormatTopic 조회 및 변환
	        List<FormatTopic> formatTopics = formatTopicRepository.findByCampaignTopic(campaignTopic);
	        if (!formatTopics.isEmpty()) {
	            List<AddFormatTopicDTO> formatTopicDTOs = new ArrayList<>();
	            
	            for (FormatTopic formatTopic : formatTopics) {
	                AddFormatTopicDTO formatTopicDTO = new AddFormatTopicDTO();
	                formatTopicDTO.setFormatId(formatTopic.getFormatId());
	                
	                // FilterTopic 조회 및 변환
	                List<FilterTopic> filterTopics = filterTopicRepository.findByFormatTopic(formatTopic);
	                if (!filterTopics.isEmpty()) {
	                    List<AddFilterTopicDTO> filterTopicDTOs = new ArrayList<>();
	                    
	                    for (FilterTopic filterTopic : filterTopics) {
	                        AddFilterTopicDTO filterTopicDTO = new AddFilterTopicDTO();
	                        filterTopicDTO.setFilterId(filterTopic.getFilterId());
	                        filterTopicDTOs.add(filterTopicDTO);
	                    }
	                    formatTopicDTO.setAddFilterTopics(filterTopicDTOs);
	                }
	                formatTopicDTOs.add(formatTopicDTO);
	            }
	            campaignTopicDTO.setAddFormatTopics(formatTopicDTOs);
	        }
	        dto.setAddcampaignTopic(campaignTopicDTO);
	    }
		
		return dto;
	}
	
	//파이프라인 시작과 정지
	public ProcessStartDTO processStartControl(ProcessStartDTO dto) {
		
		Optional<Pipelines> entity = pipelinesRepository.findById(dto.getId());
		entity.get().setExecutable(dto.isExecutable());
		
		
		AddPipelineDTO topics = findpipelinebykeyid(dto.getId());
		
		if(dto.isExecutable() == true) { //true면 실행하게  해서 이벤트 발생시켜서 토픽들 생성하고, 컨슈머 생성까지하게
			eventPublisher.publishEvent(new PipelineStartEvent(this, topics)); 
		}else {// false 면 중단 이벤트 발생시켜서 컨슈머 중지 시키기
			eventPublisher.publishEvent(new PipelineStopEvent(this, topics)); 
		}
		
		return dto;
	}
	
	//파이프라인id 로 찾아서 해당 캠페인,포맷,필터 id들만뽑아서 카프카로 보내게 간단하게 매핑시키기
}
