package com.name.blog.core.repository.custom.impl;

import com.name.blog.core.repository.custom.PostTagRepositoryCustom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static com.name.blog.core.entity.QPostTag.postTag;

@RequiredArgsConstructor
@Repository
public class PostTagRepositoryCustomImpl implements PostTagRepositoryCustom {

}
