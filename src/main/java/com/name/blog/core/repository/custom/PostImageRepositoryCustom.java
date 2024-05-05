package com.name.blog.core.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface PostImageRepositoryCustom {
    Long updateNotUsingByPostId(Long postId, Long expiresAt);
    Long updateNotUsingByPostIdIn(List<Long> postIdList, Long expiresAt);
    Long updateUsingByPostIdAndUriIn(Long postId, List<String> uriList);
    List<Tuple> findExpiredIdAndNameList();
    Long deleteByIdIn(List<Long>idList);
}
