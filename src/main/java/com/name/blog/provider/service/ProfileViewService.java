package com.name.blog.provider.service;

import com.name.blog.core.entity.ProfileView;
import com.name.blog.core.repository.ProfileViewRepository;
import com.name.blog.provider.dto.ProfileViewDTO;
import com.name.blog.provider.useCase.ProfileViewUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileViewService implements ProfileViewUseCase {
    private final ProfileViewRepository profileViewRepository;

    @Override
    public Optional<ProfileViewDTO> selectProfileViewByUsername(String username) {
        Optional<ProfileView> optionalProfileView = profileViewRepository.findByUsername(username);

        if(optionalProfileView.isPresent()) {
            return Optional.ofNullable(ProfileViewDTO.of(optionalProfileView.get()));
        } else {
            return Optional.empty();
        }
    }
}
