package com.name.blog.core.repository.custom;

import com.name.blog.core.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findValid();
    Long updateDeletingById(Long id, Long expiresAt);
    Long updateDeletingByIdIn(List<Long> idList, Long expiresAt);
}
