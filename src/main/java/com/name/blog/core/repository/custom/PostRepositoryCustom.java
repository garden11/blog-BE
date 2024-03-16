package com.name.blog.core.repository.custom;

import java.util.List;

public interface PostRepositoryCustom {
    Long updateDeletingById(Long id, Long expiresAt);
    Long updateDeletingByIdIn(List<Long> idList, Long expiresAt);
}
