package com.wnsud9771.entity.connect;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FormatConnect {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private Long fotmatKey;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaignConnect_id")
    private CampaignConnect campaignConnect;
	
	@OneToMany(mappedBy = "formatConnect", cascade = CascadeType.ALL)
    private List<FilterConnect> filterConnect;
}
