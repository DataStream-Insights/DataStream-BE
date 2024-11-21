package com.wnsud9771.service.pipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import com.wnsud9771.dto.pipeline.ProcessStartDTO;
import com.wnsud9771.dto.pipeline.add.AddFilterTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddFormatTopicDTO;
import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;
import com.wnsud9771.dto.pipeline.search.PipelinesDTO;
import com.wnsud9771.dto.pipeline.search.SearchCampaignTopicDTO;
import com.wnsud9771.dto.pipeline.search.SearchFilterTopicDTO;
import com.wnsud9771.dto.pipeline.search.SearchFormatTopicDTO;
import com.wnsud9771.dto.pipeline.search.SearchPipelineDTO;
import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.Formatentity.FormatManagement;
import com.wnsud9771.entity.connect.CampaignConnect;
import com.wnsud9771.entity.connect.FormatConnect;
import com.wnsud9771.entity.pipelineentity.CampaignTopic;
import com.wnsud9771.entity.pipelineentity.FilterTopic;
import com.wnsud9771.entity.pipelineentity.FormatTopic;
import com.wnsud9771.entity.pipelineentity.Pipelines;
import com.wnsud9771.event.pipeline.PipelineStartEvent;
import com.wnsud9771.event.pipeline.PipelineStopEvent;
import com.wnsud9771.reoisitory.campaign.CampaignRepository;
import com.wnsud9771.reoisitory.filter.FilterManagementRepository;
import com.wnsud9771.reoisitory.format.FormatManagementRepository;
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
	private final CampaignRepository campaignRepository;
	private final FormatManagementRepository formatManagementRepository;
	private final FilterManagementRepository filterManagementRepository;
	private final ConvertSendTopicsService convertSendTopicsService;

	private final ApplicationEventPublisher eventPublisher;
	private final ConnectCFFService connectCFFService;

	// 파이프라인 저장
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
			CampaignConnect campaignConnect = connectCFFService.setconnectCampaign(dto.getAddcampaignTopic().getCampaignId());

			// FormatTopic 저장 + pipeline 연결
			if (dto.getAddcampaignTopic().getAddFormatTopics() != null) {
				for (AddFormatTopicDTO addformatTopicdto : dto.getAddcampaignTopic().getAddFormatTopics()) {
					FormatTopic formatTopic = new FormatTopic();
					formatTopic.setCampaignId(dto.getAddcampaignTopic().getCampaignId());
					formatTopic.setFormatId(addformatTopicdto.getFormatId());
					formatTopic.setCampaignTopic(campaignTopic);
					formatTopic = formatTopicRepository.save(formatTopic);

					FormatConnect formatConnect = connectCFFService.setconnectFormat(campaignConnect,formatTopic.getFormatId());

					// 4. FilterTopic 저장
					if (addformatTopicdto.getAddFilterTopics() != null) {
						for (AddFilterTopicDTO filterTopicdto : addformatTopicdto.getAddFilterTopics()) {
							FilterTopic filterTopic = new FilterTopic();
							filterTopic.setFilterId(filterTopicdto.getFilterId());
							filterTopic.setFormatId(addformatTopicdto.getFormatId());
							filterTopic.setFormatTopic(formatTopic);
							filterTopicRepository.save(filterTopic);

							connectCFFService.setconnectFilter(formatConnect, filterTopic.getFilterId());

						}
					}
				}
			}
		}

		return dto;
	}

	// 파이프라인 목록 조회
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

	// 파이프라인 상세조회
	public SearchPipelineDTO findpipelinebykeyidwithname(Long id) {
		SearchPipelineDTO dto = new SearchPipelineDTO();

		Pipelines pipeline = pipelinesRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Pipeline not found with id: " + id));

		dto.setPipelineId(pipeline.getPipelineId());
		dto.setPipelineName(pipeline.getName());

		CampaignTopic campaignTopic = campaignTopicRepository.findByPipelines(pipeline);
		if (campaignTopic != null) {
			SearchCampaignTopicDTO campaignTopicDTO = new SearchCampaignTopicDTO();
			campaignTopicDTO.setCampaignId(campaignTopic.getCampaignId());
			campaignTopicDTO.setCampaignName(findcampaignName(campaignTopic.getCampaignId()));

			// FormatTopic 조회 및 변환
			List<FormatTopic> formatTopics = formatTopicRepository.findByCampaignTopic(campaignTopic);
			if (!formatTopics.isEmpty()) {
				List<SearchFormatTopicDTO> formatTopicDTOs = new ArrayList<>();

				for (FormatTopic formatTopic : formatTopics) {
					SearchFormatTopicDTO formatTopicDTO = new SearchFormatTopicDTO();
					formatTopicDTO.setFormatId(formatTopic.getFormatId());
					formatTopicDTO.setFormatName(findformatName(formatTopic.getFormatId()));

					// FilterTopic 조회 및 변환
					List<FilterTopic> filterTopics = filterTopicRepository.findByFormatTopic(formatTopic);
					if (!filterTopics.isEmpty()) {
						List<SearchFilterTopicDTO> filterTopicDTOs = new ArrayList<>();

						for (FilterTopic filterTopic : filterTopics) {
							SearchFilterTopicDTO filterTopicDTO = new SearchFilterTopicDTO();
							filterTopicDTO.setFilterId(filterTopic.getFilterId());
							filterTopicDTO.setFilterName(findfilterName(filterTopic.getFilterId()));
							filterTopicDTOs.add(filterTopicDTO);
						}
						formatTopicDTO.setSearchFilterTopics(filterTopicDTOs);
					}
					formatTopicDTOs.add(formatTopicDTO);
				}
				campaignTopicDTO.setSearchFormatTopics(formatTopicDTOs);
			}
			dto.setSearchCampaignTopic(campaignTopicDTO);
		}

		return dto;
	}

	// 파이프라인 시작과 정지
	public ProcessStartDTO processStartControl(ProcessStartDTO dto) {

		Optional<Pipelines> entity = pipelinesRepository.findById(dto.getId());
		entity.get().setExecutable(dto.isExecutable());

		AddPipelineDTO topics = convertSendTopicsService.findpipelinebykeyid(dto.getId());

		if (dto.isExecutable() == true) { // true면 실행하게 해서 이벤트 발생시켜서 토픽들 생성하고, 컨슈머 생성까지하게
			eventPublisher.publishEvent(new PipelineStartEvent(this, topics));
		} else {// false 면 중단 이벤트 발생시켜서 컨슈머 중지 시키기
			eventPublisher.publishEvent(new PipelineStopEvent(this, topics));
		}

		return dto;
	}

	// 파이프라인id 로 찾아서 해당 캠페인,포맷,필터 id들만뽑아서 카프카로 보내게 간단하게 매핑시키기

	// id로 이름들찾기
	private String findcampaignName(String campaignId) {
		Campaign entity = campaignRepository.findByCampaignId(campaignId).get();

		return entity.getCampaign_name();
	}

	private String findformatName(String formatId) {
		FormatManagement entity = formatManagementRepository.findByFormatID(formatId).get();

		return entity.getFormatname();
	}

	private String findfilterName(String filterId) {
		FilterManagement entity = filterManagementRepository.findByFilterManageId(filterId).get();

		return entity.getFilterName();
	}
}
