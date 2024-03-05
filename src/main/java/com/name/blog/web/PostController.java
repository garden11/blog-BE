package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.provider.dto.PostDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

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
	public void deletePost(@PathVariable("id") Long id) {
		postService.deletePostById(id);
	}
	
	@GetMapping("/api/v1/user/{username}/post/{id}/check-post")
	public boolean checkPost(@PathVariable("username") String username, @PathVariable("id") Long id) {
		return postService.isValidPost(username, id);
	}

	@GetMapping("/api/v1/post-view/{id}")
	public Optional<PostDetailDTO> getPostViewById(@PathVariable("id") Long id) {
		return postService.selectPostDetailById(id);
	}

	@GetMapping("/api/v1/user/{username}/post-views")
	public Page<PostDetailDTO> getPostViewsByUsername(@PathVariable("username") String username
			, @RequestParam(value="page", defaultValue="0") Integer page) {
		return postService.selectPostDetailListByUsername(username, page);
	}

	@GetMapping("/api/v1/user/{username}/category/{category-id}/post-views")
	public Page<PostDetailDTO> getPostViewsByCategoryId(@PathVariable("category-id") Long categoryId
			, @RequestParam(value="page", defaultValue="0") Integer page) {
		return postService.selectPostDetailListByCategoryId(categoryId, page);
	}
}
