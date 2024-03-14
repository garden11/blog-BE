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
    public Long updateDeletingById(Long id) {

        return queryFactory
                .update(comment)
                .set(comment.deleteYN, "Y")
                .where(comment.id.eq(id))
                .execute();
    }
}
