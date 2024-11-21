package com.wnsud9771.entity.connect;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class CampaignConnect {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		
	
	private Long campaignKey;
	
	@OneToMany(mappedBy = "campaignConnect", cascade = CascadeType.ALL)
    private List<FormatConnect> formatConnectlist;
}
