package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.provider.dto.ProfileImageDTO;
import com.name.blog.provider.service.ProfileImageService;
import com.name.blog.web.dto.ProfileImageRequestDTO;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class ProfileImageController {
	private final ProfileImageService profileImageService;
	
	@PostMapping("/api/v1/profile-image")
	@Auth(roles = {Role.USER})
	public ProfileImageDTO createProfileImage(ProfileImageRequestDTO profileImageRequestDTO) {
		return profileImageService.insertProfileImage(profileImageRequestDTO);
	}
}
