package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.entity.PostTagInfo;
import com.name.blog.core.entity.QTag;
import com.name.blog.core.repository.custom.PostTagInfoRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static  com.name.blog.core.entity.QPostTagInfo.postTagInfo;
import static  com.name.blog.core.entity.QPostTag.postTag;
import static  com.name.blog.core.entity.QTag.tag;

@RequiredArgsConstructor
@Repository
public class PostTagInfoRepositoryImpl implements PostTagInfoRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<PostTagInfo> findByPostId(Long postId) {

        return selectPostTagInfo()
                .where(postTag.postId.eq(postId))
                .fetch();
    }

    private JPAQuery<PostTagInfo> selectPostTagInfo() {

        return jpaQueryFactory
                .select(Projections.constructor(
                        PostTagInfo.class,
                        postTag.id,
                        postTag.postId,
                        postTag.tagId,
                        tag.name))
                .from(postTag)
                .join(tag)
                .on(postTag.tagId.eq(tag.id));
    }
}
