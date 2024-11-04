package com.wnsud9771.reoisitory.format;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wnsud9771.entity.Campaignentity.Category1;
import com.wnsud9771.entity.Formatentity.FieldName;

public interface FieldNameRepository extends JpaRepository<FieldName, Long>{
//	List<FieldName> findByTitle(String title);
//    @Query("SELECT DISTINCT l.title FROM FieldName l")
//    List<String> findDistinctTitle();
	List<FieldName> findByTitle(String title);
	boolean existsByTitleAndFieldnameAndItemcontentsex(String title, String fieldname, String itemcontentsex);
}
