package com.wnsud9771.entity.pipelineentity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CampaignTopic {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String campaignId; //만들 캠페인 id
	private String topicName; //생성된 캠페인 토픽이름
	
	@Column(columnDefinition = "boolean default false")
	private boolean consumer; //컨슈머 존재 기록
	
	private String autoOffset; //earlist로할지, latest로할지 구분
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "pipelines_id") 
	private Pipelines pipelines;
}
