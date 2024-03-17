package com.name.blog.core.repository;

import com.name.blog.core.entity.Tag;
import com.name.blog.core.repository.custom.TagRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long>, TagRepositoryCustom {
    Optional<Tag> findByName(String name);
}
