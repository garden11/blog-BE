package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.entity.Post;
import com.name.blog.core.entity.PostInfo;
import com.name.blog.core.repository.custom.PostInfoRepositoryCustom;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.name.blog.core.entity.QPost.post;
import static com.name.blog.core.entity.QPostImage.postImage;
import static com.name.blog.core.entity.QPostTag.postTag;


@RequiredArgsConstructor
@Repository
public class PostInfoRepositoryImpl implements PostInfoRepositoryCustom {
    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<PostInfo> findById(Long id) {
        return Optional.ofNullable(
                selectPostInfo()
                        .where(post.id.eq(id))
                        .fetchOne());
    }

    @Override
    public Page<PostInfo> findOrderByIdDesc(Pageable pageable) {
        List<PostInfo> postInfoList = selectPostInfo()
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = (long) selectPost()
                .fetch()
                .size();

        return new PageImpl<>(postInfoList, pageable, totalCount);
    }


    @Override
    public Page<PostInfo> findByUsernameOrderByIdDesc(String username, Pageable pageable) {

        List<PostInfo> postInfoList = selectPostInfo()
                .where(post.username.eq(username))
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = (long) selectPost()
                .where(post.username.eq(username))
                .fetch()
                .size();

        return new PageImpl<>(postInfoList, pageable, totalCount);
    }

    @Override
    public Page<PostInfo> findByTagIdOrderByIdDesc(Long tagId, Pageable pageable) {

        List<PostInfo> postInfoList = selectPostInfo()
                .join(postTag)
                .on(post.id.eq(postTag.postId))
                .where(postTag.tagId.eq(tagId))
                .orderBy(post.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        Long totalCount = (long) selectPost()
                .join(postTag)
                .on(post.id.eq(postTag.postId))
                .where(postTag.tagId.eq(tagId))
                .fetch()
                .size();

        return new PageImpl<>(postInfoList, pageable, totalCount);
    }


    private JPAQuery<PostInfo> selectPostInfo() {

        return queryFactory
                .select(Projections.constructor(
                        PostInfo.class,
                        post.id,
                        post.username,
                        post.title,
                        post.content,
                        post.registeredAt,
                        post.updatedAt,
                        postImage.id.max().as("thumbnailImageId"),
                        postImage.uri.max().as("thumbnailImageUri")
                ))
                .from(post)
                .leftJoin(postImage)
                .on(post.id.eq(postImage.postId)
                        .and(postImage.useYN.eq("Y")))
                .where(post.deleteYN.eq("N").and(post.registerYN.eq("Y")))
                .groupBy(post.id);
    }

    private JPAQuery<Post> selectPost() {

        return queryFactory
                .selectFrom(post)
                .where(post.deleteYN.eq("N").and(post.registerYN.eq("Y")));
    }
}
