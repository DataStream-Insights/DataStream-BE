package com.wnsud9771.reoisitory.item;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.item.FormatItem;

public interface FormatItemRepository extends JpaRepository<FormatItem, Long>{
	 Optional<FormatItem> findByPath(String path);
}
