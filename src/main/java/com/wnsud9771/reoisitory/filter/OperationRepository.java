package com.wnsud9771.reoisitory.filter;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.FIlterentity.Operation;

public interface OperationRepository extends JpaRepository<Operation, Long> {
	Optional<Operation> findByOperation(String operation);
}
