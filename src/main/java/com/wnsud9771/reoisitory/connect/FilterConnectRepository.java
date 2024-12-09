package com.wnsud9771.reoisitory.connect;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.connect.FilterConnect;

public interface FilterConnectRepository extends JpaRepository<FilterConnect, Long>{
	List<FilterConnect> findAllByFormatConnect_Id(Long formatConnectId);
	void deleteByfilterKey(Long filterKey);
}
