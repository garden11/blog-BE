package com.name.blog.core.repository;

import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.core.repository.custom.ProfileInfoRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileInfoRepository extends JpaRepository<ProfileInfo, Long>, ProfileInfoRepositoryCustom {
}
