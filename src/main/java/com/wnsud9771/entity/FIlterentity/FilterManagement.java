package com.wnsud9771.entity.FIlterentity;

import java.time.LocalDate;

import com.wnsud9771.entity.Authorentity.Author;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
public class FilterManagement {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	private String filter_manage_id; //필터링 ID
	private String filter_name;
	
	@ManyToOne
	@JoinColumn(name = "filter_id")
	private Filter filter;
	
	
	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;// 기안자
	
	@Column(name = "created_date")
	private LocalDate write_date; 
	
	
}
