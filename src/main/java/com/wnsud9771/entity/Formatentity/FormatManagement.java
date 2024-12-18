package com.wnsud9771.entity.Formatentity;

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
public class FormatManagement { //format board
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	
	private int start; //서브 스트링 시작
	private int end; //서브 스트링 끝
	private String formatname; //format명
	private String formatID; //formatID
	private String formatexplain; //포맷 설명
	//private String filetype; //파일 형식
	
	//format item
	@OneToMany(mappedBy = "formatManagement", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormatSet> formatSets = new ArrayList<>();
	
	public void setFormatSets(List<FormatSet> formatSets) {
        this.formatSets = formatSets;
        if (formatSets != null) {
            formatSets.forEach(formatSet -> formatSet.setFormatManagement(this));
        }
    }

    // 기존 단일 FormatSet 추가 메서드는 유지
    public void addFormatSet(FormatSet formatSet) {
        if (this.formatSets == null) {
            this.formatSets = new ArrayList<>();
        }
        if (!this.formatSets.contains(formatSet)) {
            this.formatSets.add(formatSet);
            if (formatSet.getFormatManagement() != this) {
                formatSet.setFormatManagement(this);
            }
        }
    }
	

}
