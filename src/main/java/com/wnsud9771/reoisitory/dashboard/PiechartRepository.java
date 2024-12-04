package com.wnsud9771.reoisitory.dashboard;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.graph.Piechart;

public interface PiechartRepository extends JpaRepository<Piechart, Long>{
	Piechart findAllByPipelinesId (Long pipelinesId);
	void deleteByPipelinesId(Long pipelinesId);
}
