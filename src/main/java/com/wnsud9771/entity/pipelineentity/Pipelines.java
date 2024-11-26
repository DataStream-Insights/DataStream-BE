package com.wnsud9771.entity.pipelineentity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Pipelines {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(columnDefinition = "boolean default false")
	private boolean executable; // 이 파이프라인 실행할건지 아닌지

	@Column(columnDefinition = "boolean default false")
	private boolean status; // 이 파이프라인 수행 상태 , 실행중인지 아닌지

	private String name; // 파이프라인 이름
	private String pipelineId; // 파이프라인 id
	
	private Long distinctCode;

	@CreatedDate
	private LocalDateTime writeDate; // 만든날짜

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "campaignTopic_id") 
	private CampaignTopic campaignTopic;
}
