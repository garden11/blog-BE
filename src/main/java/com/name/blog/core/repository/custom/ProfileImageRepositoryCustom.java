package com.name.blog.core.repository.custom;

import com.querydsl.core.Tuple;

import java.util.List;

public interface ProfileImageRepositoryCustom {
    Long updateNotUsingByIdIn(List<Long> idList);
    List<Tuple> findIdAndNameListByIdExpired(Long id);
    Long deleteByIdIn(List<Long>idList);
}
