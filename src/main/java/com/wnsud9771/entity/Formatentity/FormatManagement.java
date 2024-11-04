package com.wnsud9771.entity.Formatentity;

import java.util.ArrayList;
import java.util.List;

import com.wnsud9771.entity.item.FormatItem;

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
public class FormatManagement { //format board
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	private int start; //서브 스트링 시작
	private int end; //서브 스트링 끝
	private String formatname; //format명
	private String formatID; //formatID
	private String formatexplain; //포맷 설명
	private String filetype; //파일 형식
	
	
	
	//Formatitem
	@OneToMany(mappedBy = "formatManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormatItem> formatItems = new ArrayList<>();

}
