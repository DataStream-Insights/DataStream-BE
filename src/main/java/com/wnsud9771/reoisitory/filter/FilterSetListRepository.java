package com.wnsud9771.reoisitory.filter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.FIlterentity.FilterSetList;

public interface FilterSetListRepository extends JpaRepository<FilterSetList, Long>{
	Optional<FilterSetList> findByFilterManagementId(Long managementId);
}
