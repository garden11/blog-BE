package com.name.blog.web;

import com.name.blog.provider.dto.ProfileDetailDTO;
import com.name.blog.provider.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/api/v1/user/{username}/profile-view")
    public Optional<ProfileDetailDTO> getUserProfile(@PathVariable("username") String username) {
        return profileService.selectUserProfileByUsername(username);
    }
}
