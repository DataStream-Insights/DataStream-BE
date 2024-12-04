package com.wnsud9771.reoisitory.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.graph.Priceboard;

public interface PriceboardRepository extends JpaRepository<Priceboard, Long>{
	List<Priceboard> findAllByPipelinesId (Long pipelinesId);
}
