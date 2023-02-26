package com.name.blog.provider.useCase;

import com.name.blog.provider.dto.ProfileImageDTO;
import com.name.blog.web.dto.ProfileImageRequestDTO;

import javax.transaction.Transactional;
import java.util.Optional;

public interface ProfileImageUseCase {
    @Transactional
    ProfileImageDTO insertProfileImage(ProfileImageRequestDTO profileImageRequestDTO);
}
