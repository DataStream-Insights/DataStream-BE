package com.wnsud9771.entity.Formatentity;

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
public class FormatSet {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatManagement_id")
	private FormatManagement formatManagement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatitem_id")
	private FormatItem formatItem;
}
