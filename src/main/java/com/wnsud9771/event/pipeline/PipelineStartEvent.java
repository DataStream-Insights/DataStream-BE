package com.wnsud9771.event.pipeline;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEvent;

import com.wnsud9771.dto.pipeline.add.AddPipelineDTO;

import lombok.Getter;
@Getter
public class PipelineStartEvent  extends ApplicationEvent{
	private static final long serialVersionUID = 1L;

	private final AddPipelineDTO dto;
	private final LocalDateTime eventTime;

	public PipelineStartEvent(Object source, AddPipelineDTO dto) {
		super(source);
		this.dto = dto;
		this.eventTime = LocalDateTime.now();
	}
}


