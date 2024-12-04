package com.wnsud9771.reoisitory.dashboard;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.dashboard.graph.Treemap;

public interface TreemapRepository extends JpaRepository<Treemap, Long>{
	List<Treemap> findAllByPipelinesId (Long pipelinesId);
	void deleteByPipelinesId(Long pipelinesId);
}
