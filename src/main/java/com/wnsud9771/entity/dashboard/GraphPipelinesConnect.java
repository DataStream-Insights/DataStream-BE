package com.wnsud9771.entity.dashboard;

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

@Getter
@Setter
@Entity
public class GraphPipelinesConnect {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pipeline_id")
	private Pipelines pipelines;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "graph_list_id")
	private GraphList graphList;
}
