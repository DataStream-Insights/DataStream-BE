package com.wnsud9771.entity.dashboard;

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
public class GraphList {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String graphName;
	private String graphExplain;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pipelines_id")
	private GraphPipelinesConnect graphPipelinesConnect;
}
