package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.provider.dto.PostDTO;
import org.springframework.web.bind.annotation.*;

import com.name.blog.provider.dto.PostImageDTO;
import com.name.blog.provider.service.PostImageService;
import com.name.blog.web.dto.PostImageRequestDTO;

import lombok.RequiredArgsConstructor;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostImageController {
	private final PostImageService postImageService;
	
	@PostMapping("/api/v1/post-image")
	@Auth(roles = {Role.USER})
	public PostImageDTO createPostImage(PostImageRequestDTO postImageRequestDTO) {
		return postImageService.createPostImage(postImageRequestDTO);
	}
}
