package com.wnsud9771.reoisitory;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Category1;
import com.wnsud9771.entity.Category2;


public interface Category2Repository extends JpaRepository<Category2, Long>{
	List<Category2> findByCategory1(Category1 category1);
}
