package com.wnsud9771.reoisitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wnsud9771.entity.Authorentity.Author;

public interface AuthorRepository extends JpaRepository<Author, Long>{
	 Optional<Author> findById(Long id);
}
