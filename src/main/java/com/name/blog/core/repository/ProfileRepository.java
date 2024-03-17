package com.name.blog.core.repository;

import com.name.blog.core.entity.Profile;
import com.name.blog.core.repository.custom.ProfileRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long>, ProfileRepositoryCustom {
    Optional<Profile> findByUsername(String username);
}
