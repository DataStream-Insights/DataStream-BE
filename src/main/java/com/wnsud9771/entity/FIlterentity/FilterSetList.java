package com.wnsud9771.entity.FIlterentity;

import java.util.ArrayList;
import java.util.List;

import com.wnsud9771.entity.item.FormatItem;

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
public class FilterSetList {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@OneToMany(mappedBy = "filterSetList", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilterSet> filterSets = new ArrayList<>();
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filtermanagement_id")
	private FilterManagement filterManagement;
}
