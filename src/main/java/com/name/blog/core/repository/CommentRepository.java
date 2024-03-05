package com.name.blog.core.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Page<Comment> findByPostId(Long postId, Pageable pageable);
    @Modifying
    @Query(value="UPDATE comment SET delete_yn = 'Y' WHERE id = :id", nativeQuery=true)
    int updateDeleteYById(@Param("id") Long id);
}
