package com.name.blog.core.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {}
