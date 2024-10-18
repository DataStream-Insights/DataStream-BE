package com.wnsud9771.entity;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Component
public class Campaign {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "campaign_id")
    private String campaign_id; //캠페인 id

    @ManyToOne
    @JoinColumn(name = "category1_id")
    private Category1 category1;
    
    @ManyToOne
    @JoinColumn(name = "category2_id")
    private Category2 category2;

    @Column(name = "campaign_name")
    private String campaign_name; // 캠페인 명

    private String status; // 상태

    @Column(name = "start_date")
    private LocalDate start_date; //시작일자

    @Column(name = "end_date")
    private LocalDate end_date; // 종료일자

    @Column(name = "is_public")
    private String is_public; // 공개상태

    private String department; // 기안부서
    private String author; // 기안자

    @Column(name = "created_date")
    private LocalDate write_date; //기안일자	

//    @ElementCollection
//    private List<String> tags;
}
