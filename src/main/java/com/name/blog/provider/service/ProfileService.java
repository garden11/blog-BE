package com.name.blog.provider.service;

import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.core.repository.ProfileInfoRepository;
import com.name.blog.provider.dto.ProfileDetailDTO;
import com.name.blog.provider.useCase.ProfileUseCase;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProfileService implements ProfileUseCase {
    private final ProfileInfoRepository profileInfoRepository;

    @Override
    @Transactional
    public Optional<ProfileDetailDTO> getUserProfileByUsername(String username) {
        Optional<ProfileInfo> optionalUProfileDetail = profileInfoRepository.findByUsername(username);

        if(optionalUProfileDetail.isPresent()) {
            return Optional.ofNullable(ProfileDetailDTO.of(optionalUProfileDetail.get()));
        } else {
            return Optional.empty();
        }
    }
}
