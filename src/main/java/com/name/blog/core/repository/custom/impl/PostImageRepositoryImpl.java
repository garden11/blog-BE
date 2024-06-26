package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.PostImageRepositoryCustom;
import com.name.blog.util.DateUtil;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.name.blog.core.entity.QPostImage.postImage;

@RequiredArgsConstructor
@Repository
public class PostImageRepositoryImpl implements PostImageRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateNotUsingByPostId(Long postId, Long expiresAt) {

        return queryFactory
                .update(postImage)
                .set(postImage.useYN, "N")
                .set(postImage.expiresAt, expiresAt)
                .where(postImage.postId.eq(postId))
                .execute();
    }

    @Override
    public Long updateNotUsingByPostIdIn(List<Long> postIdList, Long expiresAt) {
        return queryFactory
                .update(postImage)
                .set(postImage.useYN, "N")
                .set(postImage.expiresAt, expiresAt)
                .where(postImage.postId.in(postIdList))
                .execute();
    }

    @Override
    public Long updateUsingByPostIdAndUriIn(Long postId, List<String> uriList) {
        return queryFactory
                .update(postImage)
                .set(postImage.useYN, "Y")
                .setNull(postImage.expiresAt)
                .where(postImage.postId.eq(postId).and(postImage.uri.in(uriList)))
                .execute();
    }

    @Override
    public List<Tuple> findExpiredIdAndNameList() {

        return queryFactory
                 .select(postImage.id, postImage.name)
                 .from(postImage)
                 .where(postImage.expiresAt.isNotNull()
                        .and(postImage.expiresAt.lt(Expressions.asNumber(System.currentTimeMillis()))))
                 .fetch();
    }

    @Override
    public Long deleteByIdIn(List<Long> idList) {

        return queryFactory
                .delete(postImage)
                .where(postImage.id.in(idList))
                .execute();
    }
}
