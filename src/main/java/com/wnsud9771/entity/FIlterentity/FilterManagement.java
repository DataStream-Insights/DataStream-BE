package com.wnsud9771.entity.FIlterentity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FilterManagement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	private String filter_manage_id; //필터링 ID
	private String filter_name;
	
	@Column(name = "created_date")
	private LocalDate writedate; 
	
	
//	//author
//	@ManyToOne
//	@JoinColumn(name = "author_id")
//	private Author author;// 기안자
	
	@OneToMany(mappedBy = "filterManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilterSet> filterSets = new ArrayList<>();
	
	
}
