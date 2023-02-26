package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.ProfileViewDTO;

import java.util.Optional;

public interface ProfileViewUseCase {
    Optional<ProfileViewDTO> selectProfileViewByUsername(String username);
}
