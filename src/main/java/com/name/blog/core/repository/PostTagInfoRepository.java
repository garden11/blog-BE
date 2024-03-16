package com.name.blog.core.repository;

import com.name.blog.core.entity.PostTagInfo;
import com.name.blog.core.repository.custom.PostTagInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostTagInfoRepository extends JpaRepository<PostTagInfo, Long>, PostTagInfoRepositoryCustom {
}
