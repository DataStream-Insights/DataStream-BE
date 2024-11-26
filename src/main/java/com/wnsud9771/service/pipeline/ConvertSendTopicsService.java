package com.wnsud9771.service.pipeline;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.wnsud9771.dto.pipeline.add.AddCampaignTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddFilterTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddFormatTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;
import com.wnsud9771.entity.pipelineentity.CampaignTopic;
import com.wnsud9771.entity.pipelineentity.FilterTopic;
import com.wnsud9771.entity.pipelineentity.FormatTopic;
import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.reoisitory.pipeline.CampaignTopicRepository;
import com.wnsud9771.reoisitory.pipeline.FilterTopicRepository;
import com.wnsud9771.reoisitory.pipeline.FormatTopicRepository;
import com.wnsud9771.reoisitory.pipeline.PipelinesRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor

public class ConvertSendTopicsService {
	private final PipelinesRepository pipelinesRepository;
	private final CampaignTopicRepository campaignTopicRepository;
	private final FormatTopicRepository formatTopicRepository;
	private final FilterTopicRepository filterTopicRepository;
	
	public AddPipelineDTO findpipelinebykeyid(Long id) {
		AddPipelineDTO dto = new AddPipelineDTO();
		
	    Pipelines pipeline = pipelinesRepository.findById(id)
	            .orElseThrow(() -> new EntityNotFoundException("Pipeline not found with id: " + id));
	    
	    dto.setPipelineId(pipeline.getPipelineId());
	    dto.setPipelineName(pipeline.getName());
	    dto.setDistinctCode(pipeline.getDistinctCode());
		
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
}
