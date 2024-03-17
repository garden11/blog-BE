package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.core.repository.custom.ProfileInfoRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.name.blog.core.entity.QProfile.profile;
import static com.name.blog.core.entity.QProfileImage.profileImage;

@RequiredArgsConstructor
@Repository
public class ProfileInfoRepositoryImpl implements ProfileInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<ProfileInfo> findByUsernameIn(List<String> usernameList) {

        return selectProfileInfo()
                        .where(profile.username.in(usernameList))
                        .fetch();
    }

    @Override
    public Optional<ProfileInfo> findByUsername(String username) {

        return Optional.ofNullable(
                selectProfileInfo()
                        .where(profile.username.eq(username))
                        .fetchOne()
        );
    }

    private JPAQuery<ProfileInfo> selectProfileInfo() {

        return queryFactory
                .select(Projections.constructor(
                        ProfileInfo.class,
                        profile.id,
                        profile.username,
                        profileImage.id.max().as("profileImageId"),
                        profileImage.uri.max().as("profileImageUri")
                ))
                .from(profile)
                .leftJoin(profileImage)
                .on(profile.id.eq(profileImage.profileId)
                        .and(profileImage.useYN.eq("Y")))
                .where(profile.deleteYN.eq("N"))
                .groupBy(profile.id);
    }
}
