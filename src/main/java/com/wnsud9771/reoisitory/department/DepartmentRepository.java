package com.wnsud9771.reoisitory.department;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Campaignentity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	Optional<Department> findById(Long id);
}
