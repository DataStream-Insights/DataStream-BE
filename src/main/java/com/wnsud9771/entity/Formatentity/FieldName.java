package com.wnsud9771.entity.Formatentity;

import java.util.List;

import com.wnsud9771.entity.FIlterentity.FilterItem;

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
public class FieldName {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "logTitle_id")
	private LogTitle logtitle;
	
	private String field_name; // format field의 필드명
	private String item_contents_ex; //아이템 컨텐츠 예시 == formatfiled의 아이템 컨텐츠 예시
	
	@OneToMany(mappedBy = "fieldName", cascade = CascadeType.ALL)
    private List<FilterItem> filterItems;
}
