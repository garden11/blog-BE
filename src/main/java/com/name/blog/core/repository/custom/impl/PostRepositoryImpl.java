package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.PostRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.name.blog.core.entity.QPost.post;

@RequiredArgsConstructor
@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateDeletingById(Long id, Long expiresAt) {

        return queryFactory
                .update(post)
                .set(post.deleteYN, "Y")
                .set(post.expiresAt, expiresAt)
                .where(post.id.eq(id))
                .execute();
    }

    @Override
    public Long updateDeletingByIdIn(List<Long> idList, Long expiresAt) {

        return queryFactory
                .update(post)
                .set(post.deleteYN, "Y")
                .set(post.expiresAt, expiresAt)
                .where(post.id.in(idList))
                .execute();
    }
}
