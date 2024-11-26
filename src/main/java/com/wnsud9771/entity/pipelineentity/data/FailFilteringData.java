package com.wnsud9771.entity.pipelineentity.data;

import java.time.LocalDateTime;

import com.wnsud9771.entity.pipelineentity.Pipelines;

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
public class FailFilteringData {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDateTime timestamp;
	private String data;
	private String failReason;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pipelines_id")	
	private Pipelines pipelines;
}	
