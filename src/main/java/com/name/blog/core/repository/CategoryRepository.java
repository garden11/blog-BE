package com.name.blog.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	List<Category> findByUsername(String username);

	boolean existsByUsernameAndId(String username, Long id);

	@Modifying
	@Query(value="UPDATE category SET delete_yn = 'Y' WHERE id = :id", nativeQuery=true)
	int updateDeleteYById(@Param("id") Long id);
}
