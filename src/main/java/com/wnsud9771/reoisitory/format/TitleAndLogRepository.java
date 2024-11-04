package com.wnsud9771.reoisitory.format;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Formatentity.TitleAndLog;

public interface TitleAndLogRepository extends JpaRepository<TitleAndLog, Long>{
	Optional<TitleAndLog> findByTitle(String title);
}
