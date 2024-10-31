package com.wnsud9771.entity.FIlterentity;

import java.time.LocalDate;
import java.util.List;

import com.wnsud9771.entity.Authorentity.Author;
import com.wnsud9771.entity.Campaignentity.Campaign;
import com.wnsud9771.entity.Campaignentity.Category2;
import com.wnsud9771.entity.Campaignentity.Department;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Filter {  //필터링-> 행동정의 설정
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 
	
	private String filteritem_list;
	private String operation;
	private String filteritem_type;
	
	@OneToMany(mappedBy = "filter", cascade = CascadeType.ALL)
    private List<FilterManagement> filterManagements;
}
