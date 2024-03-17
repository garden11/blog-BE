package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.CommentRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.name.blog.core.entity.QComment.comment;

@RequiredArgsConstructor
@Repository
public class CommentRepositoryImpl implements CommentRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateDeletingById(Long id, Long expiresAt) {

        return queryFactory
                .update(comment)
                .set(comment.deleteYN, "Y")
                .set(comment.expiresAt, expiresAt)
                .where(comment.id.eq(id))
                .execute();
    }

    @Override
    public Long updateDeletingByUsername(String username, Long expiresAt) {

        return queryFactory
                .update(comment)
                .set(comment.deleteYN, "Y")
                .set(comment.expiresAt, expiresAt)
                .where(comment.username.eq(username))
                .execute();
    }
}
