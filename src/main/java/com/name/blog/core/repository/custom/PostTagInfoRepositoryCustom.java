package com.name.blog.core.repository.custom;

import com.name.blog.core.entity.PostTagInfo;

import java.util.List;

public interface PostTagInfoRepositoryCustom {
    List<PostTagInfo> findByPostId(Long postId);
}
