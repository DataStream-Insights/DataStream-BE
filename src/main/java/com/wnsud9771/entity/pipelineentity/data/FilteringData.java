package com.wnsud9771.entity.pipelineentity.data;

import com.wnsud9771.entity.pipelineentity.FilterTopic;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class FilteringData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String path; 
	private String data;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filterTopic_id")
	private FilterTopic filterTopic;
}
