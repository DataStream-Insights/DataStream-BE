package com.wnsud9771.entity.Authorentity;

import java.util.List;

import org.springframework.stereotype.Component;

import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.FIlterentity.FilterManagement;

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
@Component
public class Author {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String author;
	
	@OneToMany(mappedBy = "author")
    private List<Campaign> campaigns;
	
	@OneToMany(mappedBy = "author")
	private List<FilterManagement> filterManagements;

}
