package com.wnsud9771.entity;

import java.util.List;

import org.springframework.stereotype.Component;

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
@Component
public class Category2 {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String category2;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category1_id")
    private Category1 category1;
    
    @OneToMany(mappedBy = "category2", cascade = CascadeType.ALL)
    private List<Campaign> campaigns;
}
