package com.wnsud9771.reoisitory.format;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Formatentity.FormatManagement;

public interface FormatManagementRepository extends JpaRepository<FormatManagement, Long>{
	Optional<FormatManagement> findByFormatID(String formatId);
}
