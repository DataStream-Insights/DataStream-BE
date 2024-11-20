package com.wnsud9771.reoisitory.filter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.FIlterentity.FilterManagement;
import com.wnsud9771.entity.Formatentity.FormatManagement;

public interface FilterManagementRepository extends JpaRepository<FilterManagement, Long>{
	Optional<FilterManagement> findByFilterID(String filterId);
}
