package com.wnsud9771.entity.dashboard.graph;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Priceboard {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Long averageValue;
	private Long minValue;
	private Long maxValue;
	private LocalDateTime timestamp;

	private Long pipelinesId;
}
