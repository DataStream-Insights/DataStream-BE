package com.wnsud9771.reoisitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long>{
	Optional<Department> findById(Long id);
}
