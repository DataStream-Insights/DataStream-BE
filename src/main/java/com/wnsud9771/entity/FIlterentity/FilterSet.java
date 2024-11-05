package com.wnsud9771.entity.FIlterentity;

import com.wnsud9771.entity.item.FormatItem;

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
public class FilterSet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filtermanagement_id")
	private FilterManagement filterManagement;
	
	//formatitem
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatItem_id")
	private FormatItem formatItem;
	
	//operation
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "operation_id")
	private Operation operation;
}
