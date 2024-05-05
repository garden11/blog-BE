package com.name.blog.core.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ProfileImageRepositoryCustom {
    Long updateNotUsingByProfileId(Long profileId, Long expiresAt);
    List<Tuple> findExpiredIdAndNameList();
    Long deleteByIdIn(List<Long>idList);
}
