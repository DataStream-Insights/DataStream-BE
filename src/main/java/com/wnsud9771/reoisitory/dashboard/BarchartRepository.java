package com.wnsud9771.reoisitory.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.graph.Barchart;

public interface BarchartRepository extends JpaRepository<Barchart, Long>{
	List<Barchart> findAllByPipelinesId (Long pipelinesId);
}
