package com.wnsud9771.entity.FIlterentity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class FilterManagement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	private String filterManageId; //필터링 ID
	private String filterName;
	
//	@Column(name = "created_date")
//	private LocalDate writedate; 
	
	
//	//author
//	@ManyToOne
//	@JoinColumn(name = "author_id")
//	private Author author;// 기안자
	
	@OneToOne(mappedBy = "filterManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FilterSetList filterSetList;
	
	
}
