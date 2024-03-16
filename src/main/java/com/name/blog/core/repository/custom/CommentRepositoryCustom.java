package com.name.blog.core.repository.custom;

public interface CommentRepositoryCustom {
    Long updateDeletingById(Long id, Long expiresAt);
    Long updateDeletingByUsername(String username, Long expiresAt);
}
