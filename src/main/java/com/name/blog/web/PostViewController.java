package com.name.blog.web;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.provider.dto.PostViewDTO;
import com.name.blog.provider.service.PostViewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class PostViewController {
	private final PostViewService postViewService;

	@GetMapping("/api/v1/post-view/{id}")
	public Optional<PostViewDTO> getPostViewById(@PathVariable("id") Long id) {
		return postViewService.selectPostViewById(id);
	}
	
	@GetMapping("/api/v1/user/{username}/post-views")
	public Page<PostViewDTO> getPostViewsByUsername(@PathVariable("username") String username
			, @RequestParam(value="page", defaultValue="0") Integer page) {
		return postViewService.selectPostViewListByUsername(username, page);
	}
	
	@GetMapping("/api/v1/user/{username}/category/{category-id}/post-views")
	public Page<PostViewDTO> getPostViewsByCategoryId(@PathVariable("category-id") Long categoryId
			, @RequestParam(value="page", defaultValue="0") Integer page) {
		return postViewService.selectPostViewListByCategoryId(categoryId, page);
	}
}
