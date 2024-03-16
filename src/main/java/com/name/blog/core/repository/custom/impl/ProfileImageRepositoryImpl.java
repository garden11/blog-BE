package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.ProfileImageRepositoryCustom;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.name.blog.core.entity.QProfileImage.profileImage;

@RequiredArgsConstructor
@Repository
public class ProfileImageRepositoryImpl implements ProfileImageRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateNotUsingByProfileId(Long profileId, Long expiresAt) {

        return queryFactory
                .update(profileImage)
                .set(profileImage.useYN, "N")
                .set(profileImage.expiresAt, expiresAt)
                .where(profileImage.profileId.eq(profileId))
                .execute();
    }

    @Override
    public List<Tuple> findIdAndNameListByIdExpired(Long id) {

        return queryFactory
                .select(profileImage.id, profileImage.name)
                .from(profileImage)
                .where(profileImage.expiresAt.isNotNull()
                        .and(profileImage.expiresAt.lt(Expressions.asNumber(System.currentTimeMillis())))
                        .and(profileImage.id.goe(id)))
                .fetch();
    }

    @Override
    public Long deleteByIdIn(List<Long> idList) {

        return queryFactory
                .delete(profileImage)
                .where(profileImage.id.in(idList))
                .execute();
    }
}
