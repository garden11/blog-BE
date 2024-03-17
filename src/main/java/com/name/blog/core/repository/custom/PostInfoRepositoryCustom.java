package com.name.blog.core.repository.custom;

import com.name.blog.core.entity.Post;
import com.name.blog.core.entity.PostInfo;
import com.querydsl.jpa.impl.JPAQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PostInfoRepositoryCustom {
    Optional<PostInfo> findById(Long id);
    Page<PostInfo> findByUsernameOrderByIdDesc(String username, Pageable pageable);
    Page<PostInfo> findByTagIdOrderByIdDesc(Long tagId, Pageable pageable);
    Page<PostInfo> findOrderByIdDesc(Pageable pageable);
}
