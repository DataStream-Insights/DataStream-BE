package com.wnsud9771.event;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class FilterCreatedEvent extends ApplicationEvent{
	private static final long serialVersionUID = 1L;
	
	private final String campaignId;
	private final String formatId;
	private final String filterId;
	private final LocalDateTime eventTime;

	public FilterCreatedEvent(Object source, String filterId, String formatId, String campaignId) {
		super(source);
		this.filterId = filterId;
		this.formatId = formatId;
		this.campaignId = campaignId;
		this.eventTime = LocalDateTime.now();
	}
}
