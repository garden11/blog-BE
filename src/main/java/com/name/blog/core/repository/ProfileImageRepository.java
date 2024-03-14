package com.name.blog.core.repository;

import java.util.List;
import java.util.Optional;

import com.name.blog.core.repository.custom.ProfileImageRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.name.blog.core.entity.ProfileImage;

@Repository
public interface ProfileImageRepository extends JpaRepository<ProfileImage, Long>, ProfileImageRepositoryCustom {
	List<ProfileImage> findByProfileId(Long profileId);
}
