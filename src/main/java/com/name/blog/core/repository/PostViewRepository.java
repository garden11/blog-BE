package com.name.blog.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.PostView;

@Repository
public interface PostViewRepository extends JpaRepository<PostView, Long> {
	Page<PostView> findByUsernameOrderByIdDesc(String username, Pageable pageable);

	Page<PostView> findByCategoryIdOrderByIdDesc(Long Long, Pageable pageable);
}
