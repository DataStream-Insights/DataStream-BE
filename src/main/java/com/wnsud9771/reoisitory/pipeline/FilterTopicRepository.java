package com.wnsud9771.reoisitory.pipeline;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.pipelineentity.FilterTopic;
import com.wnsud9771.entity.pipelineentity.FormatTopic;

public interface FilterTopicRepository extends JpaRepository<FilterTopic, Long>{
	 List<FilterTopic> findByFormatTopic(FormatTopic formatTopic);
}
