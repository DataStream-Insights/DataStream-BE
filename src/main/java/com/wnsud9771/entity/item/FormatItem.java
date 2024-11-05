package com.wnsud9771.entity.item;

import java.util.ArrayList;
import java.util.List;

import com.wnsud9771.entity.FIlterentity.FilterSet;
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
public class FormatItem { // 파싱 후 item db
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fieldName;
	private String itemAlias; // 아이템 별명
	private String itemExplain; // 아이템 설명
	private String itemType; // TYPE
	private String itemContent; // 아이템 컨텐츠 예시
	private String path; //field의 path
	
	
	
	// format set
	@OneToMany(mappedBy = "formatItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormatSet> formatSets = new ArrayList<>();
	
	//filter set으로 가야함
	@OneToMany(mappedBy = "formatItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FilterSet> filterSets = new ArrayList<>();
}
