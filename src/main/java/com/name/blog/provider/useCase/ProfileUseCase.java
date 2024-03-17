package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.ProfileDetailDTO;

import java.util.Optional;

public interface ProfileUseCase {
    Optional<ProfileDetailDTO> getUserProfileByUsername(String username);
}
