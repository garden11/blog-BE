package com.name.blog.core.repository.custom;

import com.name.blog.core.entity.ProfileInfo;

import java.util.List;
import java.util.Optional;

public interface ProfileInfoRepositoryCustom {
    Optional<ProfileInfo> findByUsername(String username);
    List<ProfileInfo> findByUsernameIn(List<String> usernameList);
}
