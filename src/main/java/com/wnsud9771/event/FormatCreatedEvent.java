package com.wnsud9771.event;

import java.time.LocalDateTime;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class FormatCreatedEvent  extends ApplicationEvent{
private static final long serialVersionUID = 1L;
	
	private final String formatId;
	private final String campaignId;
    private final LocalDateTime eventTime;

    public FormatCreatedEvent(Object source, String formatId,String campaignId) {
        super(source);
        this.campaignId = campaignId;
        this.formatId = formatId;
        this.eventTime = LocalDateTime.now();
    }
}
