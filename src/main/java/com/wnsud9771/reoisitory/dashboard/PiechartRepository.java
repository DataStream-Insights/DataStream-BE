package com.wnsud9771.reoisitory.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.graph.Piechart;

public interface PiechartRepository extends JpaRepository<Piechart, Long>{
	List<Piechart> findAllByPipelinesId (Long pipelinesId);
}
