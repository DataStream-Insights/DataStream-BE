package com.wnsud9771.entity.FIlterentity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.wnsud9771.entity.Authorentity.Author;
import com.wnsud9771.entity.Formatentity.FormatSet;

import jakarta.persistence.CascadeType;
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
public class Operation {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	private String operation;
	
	//filter set 으로 가야함
	@OneToMany(mappedBy = "operation", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilterSet> filterSets = new ArrayList<>();
}
