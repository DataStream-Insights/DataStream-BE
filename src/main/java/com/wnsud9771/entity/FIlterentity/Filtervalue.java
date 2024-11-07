package com.wnsud9771.entity.FIlterentity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class Filtervalue { //필터링 행동정의에서 젤 오른쪽 input창 값 저장하는 entity
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	private String value;
	
	@OneToMany(mappedBy = "filtervalue", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilterSet> filterSets = new ArrayList<>();
}
