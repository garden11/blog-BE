package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.ProfileImageDTO;
import com.name.blog.web.dto.ProfileImageRequestDTO;

import jakarta.transaction.Transactional;
import java.util.Optional;

public interface ProfileImageUseCase {
    ProfileImageDTO createProfileImage(ProfileImageRequestDTO profileImageRequestDTO);
}
