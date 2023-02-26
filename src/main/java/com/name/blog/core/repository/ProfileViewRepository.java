package com.name.blog.core.repository;

import com.name.blog.core.entity.ProfileView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileViewRepository extends JpaRepository<ProfileView, Long> {
    Optional<ProfileView> findByUsername(String username);
}
