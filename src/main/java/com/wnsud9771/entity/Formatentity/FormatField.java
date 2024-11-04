//package com.wnsud9771.entity.Formatentity;
//
//import java.util.List;
//
//import com.wnsud9771.entity.FIlterentity.FilterItem;
//
//import jakarta.persistence.CascadeType;
//import jakarta.persistence.Entity;
//import jakarta.persistence.FetchType;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.OneToMany;
//import lombok.Getter;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//public class FormatField { // 필드 설정 정보
//	@Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//	
//	private String substring; //서브스트링
//	private String substring_start; //서브스트링 시작
//	private String substring_end; //서브스트링 끝
//	private String substring_start_content;// 서브스트링 시작 빈칸 안에 데이터
//	private String substring_end_content;// 서브스트링 끝 빈칸 안에 데이터
//	private Long substring_start_count; // 서브스트링 시작 맨 오른쪽 카운트
//	private Long substring_end_count; // 서브스트링 끝 맨 오른쪽 카운트
//	private String file_format; // 파일 형식
//	
//	@OneToMany(mappedBy = "formatField", cascade = CascadeType.ALL)
//    private List<FormatManagement> formats;
//	
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "filterItem_id")
//	private FilterItem filterItem;
//	
//}
