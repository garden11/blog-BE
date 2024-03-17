package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.ProfileRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.name.blog.core.entity.QProfile.profile;

@RequiredArgsConstructor
@Repository
public class ProfileRepositoryImpl implements ProfileRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateDeletingById(Long id) {

        return queryFactory
                .update(profile)
                .set(profile.deleteYN, "Y")
                .where(profile.id.eq(id))
                .execute();
    }
}
