package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.provider.dto.PostDTO;
import com.name.blog.provider.service.PostService;
import com.name.blog.web.dto.PostRequestDTO;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostController {
	private final PostService postService;

	@GetMapping("/api/v1/post/{id}")
	public Optional<PostDTO> getPost(@PathVariable("id") Long id) {
		return postService.selectPostByPostId(id);
	}

	@PostMapping("/api/v1/post")
	@Auth(roles = {Role.USER})
	public PostDTO createPost(@RequestBody PostRequestDTO postRequestDTO) {
		return postService.insertPost(postRequestDTO);
	}

	@PutMapping("/api/v1/post/{id}")
	@Auth(roles = {Role.USER})
	public PostDTO updatePost(@PathVariable("id") Long id, @RequestBody PostRequestDTO postRequestDTO) {
		return postService.updatePostById(id, postRequestDTO);
	}
	
	@DeleteMapping("/api/v1/post/{id}")
	@Auth(roles = {Role.USER})
	public PostDTO removePost(@PathVariable("id") Long id) {
		return postService.deletePostById(id);
	}
	
	@GetMapping("/api/v1/user/{username}/post/{id}/check-post")
	public boolean checkPost(@PathVariable("username") String username, @PathVariable("id") Long id) {
		return postService.isValidPost(username, id);
	}
}
