package com.wnsud9771.entity.Formatentity;

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
public class Format { //format board
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String formatname;
	private String formatID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatField_id")
	private FormatField formatField;
}
