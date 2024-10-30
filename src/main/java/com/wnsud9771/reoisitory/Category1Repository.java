package com.wnsud9771.reoisitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wnsud9771.entity.Campaignentity.Category1;

@Repository
public interface Category1Repository extends JpaRepository<Category1, Long> {
    Optional<Category1> findByCategory1(String category1);
}
