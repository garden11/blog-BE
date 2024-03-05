package com.name.blog.core.repository;

import com.name.blog.core.entity.PostInfo;
import com.name.blog.core.repository.custom.PostInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PostInfoRepository extends JpaRepository<PostInfo, Long>, PostInfoRepositoryCustom {
    Optional<PostInfo> findById(Long id);
}
