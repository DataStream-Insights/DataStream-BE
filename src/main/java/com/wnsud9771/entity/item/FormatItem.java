package com.wnsud9771.entity.item;

import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.Formatentity.FormatManagement;

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
public class FormatItem { // 파싱 후 item db
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String fieldName;
	private String itemAlias; // 아이템 별명
	private String itemExplain; // 아이템 설명
	private String itemType; // TYPE
	private String itemContent; // 아이템 컨텐츠 예시

	// format management
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "formatmanagement_id")
	private FormatManagement formatManagement;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "filtermanagement_id")
	private FilterManagement filterManagement;
}
