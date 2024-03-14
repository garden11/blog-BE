package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.UserRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.name.blog.core.entity.QUser.user;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Long updateDeletingById(Long id) {

        return queryFactory
                .update(user)
                .set(user.deleteYN, "Y")
                .where(user.id.eq(id))
                .execute();
    }
}
