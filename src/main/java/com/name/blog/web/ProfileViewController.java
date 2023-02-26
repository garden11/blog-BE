package com.name.blog.web;

import com.name.blog.provider.dto.ProfileImageDTO;
import com.name.blog.provider.dto.ProfileViewDTO;
import com.name.blog.provider.service.ProfileViewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProfileViewController {
    private final ProfileViewService profileViewService;

    @GetMapping("/api/v1/user/{username}/profile-view")
    public Optional<ProfileViewDTO> getProfileView(@PathVariable("username") String username) {
        return profileViewService.selectProfileViewByUsername(username);
    }
}
