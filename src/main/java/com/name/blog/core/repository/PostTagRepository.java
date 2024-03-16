package com.name.blog.core.repository;

import com.name.blog.core.entity.PostTag;
import com.name.blog.core.repository.custom.PostTagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostTagRepository extends JpaRepository<PostTag, Long>, PostTagRepositoryCustom {
    List<PostTag> findByPostId(Long postId);
    void deleteByPostIdAndTagId(Long postId, Long tagId);
    boolean existsByTagId(Long tagId);
}
