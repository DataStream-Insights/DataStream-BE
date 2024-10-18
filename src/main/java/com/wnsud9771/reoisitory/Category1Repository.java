package com.wnsud9771.reoisitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wnsud9771.entity.Category1;

@Repository
public interface Category1Repository extends JpaRepository<Category1, Long>{
	
}
