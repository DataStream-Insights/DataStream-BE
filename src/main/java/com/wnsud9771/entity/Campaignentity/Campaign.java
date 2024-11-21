package com.wnsud9771.entity.Campaignentity;

import java.time.LocalDate;

import com.wnsud9771.entity.Authorentity.Author;

import jakarta.persistence.Column;
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
public class Campaign {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "campaign_id", unique = true, nullable = false, updatable = false)
	private String campaignId; // 캠페인 id

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category2_id")
	private Category2 category2;

	@Column(name = "campaign_name")
	private String campaign_name; // 캠페인 명

	private String status; // 상태

	private String customerType; // 고객군 유형

	@Column(name = "start_date")
	private LocalDate start_date; // 시작일자

	@Column(name = "end_date")
	private LocalDate end_date; // 종료일자

	@Column(name = "is_public")
	private String is_public; // 공개상태

	@ManyToOne
	@JoinColumn(name = "departement_id")
	private Department department;

	@ManyToOne
	@JoinColumn(name = "author_id")
	private Author author;// 기안자

	@Column(name = "created_date")
	private LocalDate write_date; // 기안일자

	@Column(name = "end_date_after")
	private Long end_date_after; // 종료 후

	@Column(name = "campaign_description")
	private String campaign_description; // 캠페인 설명

//	@Column(name = "tag")
//	private String tag;
	
//	@OneToMany(mappedBy = "campaign")
//	private List<CampaignFormat> campaignFormats;
	

}
