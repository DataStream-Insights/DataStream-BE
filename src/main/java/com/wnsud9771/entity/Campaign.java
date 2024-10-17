package com.wnsud9771.entity;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    private String campaign_id;

    private String category1;
    private String category2;

    @Column(name = "campaign_name")
    private String campaign_name;

    private String status;

    @Column(name = "start_date")
    private LocalDate start_date;

    @Column(name = "end_date")
    private LocalDate end_date;

    @Column(name = "is_public")
    private String is_public;

    private String department;
    private String author;

    @Column(name = "created_date")
    private LocalDate created_date;

//    @ElementCollection
//    private List<String> tags;
}
