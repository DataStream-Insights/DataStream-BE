package com.wnsud9771.entity.connect;

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
public class FilterConnect {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long filterKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "formatConnect_id")
    private FormatConnect formatConnect;
}
