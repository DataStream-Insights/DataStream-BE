package com.wnsud9771.entity.Formatentity;

import java.util.List;

import com.wnsud9771.entity.FIlterentity.FilterItem;

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
public class FieldName {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String title; //log 데이터의 제목
	private String field_name; // format field의 필드명
	private String item_contents_ex; //아이템 컨텐츠 예시 == formatfiled의 아이템 컨텐츠 예시
	
	@OneToMany(mappedBy = "fieldName", cascade = CascadeType.ALL)
    private List<FilterItem> filterItems;
}
