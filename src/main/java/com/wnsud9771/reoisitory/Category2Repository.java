package com.wnsud9771.reoisitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Category2;

public interface Category2Repository extends JpaRepository<Category2, Long> {
    Optional<Category2> findByCategory2(String category2);
}
