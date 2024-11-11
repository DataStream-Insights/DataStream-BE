package com.wnsud9771.reoisitory.mapping;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.kafka_topic.FormatFilter;

public interface FormatFilterRepository extends JpaRepository<FormatFilter, Long>{
	List<FormatFilter> findByFormatManagement_FormatID(String formatID);
}
