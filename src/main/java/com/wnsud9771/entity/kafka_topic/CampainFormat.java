package com.wnsud9771.entity.kafka_topic;

import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.Formatentity.FormatManagement;

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
public class CampainFormat {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "campaign_id")
	private Campaign campaign;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "format_id")
    private FormatManagement formatManagement;
}
