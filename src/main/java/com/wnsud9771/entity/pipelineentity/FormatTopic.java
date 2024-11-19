package com.wnsud9771.entity.pipelineentity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FormatTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String topicName; //생성된 캠페인 토픽이름
	private String campaignId; //만들 캠페인 id
	private String formatId;
	
	@Column(columnDefinition = "boolean default false")
	private boolean consumer; //컨슈머 존재 기록
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campaignTopic_id")
	private CampaignTopic campaignTopic;
}
