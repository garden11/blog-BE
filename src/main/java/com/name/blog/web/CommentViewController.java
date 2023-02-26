package com.name.blog.web;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.provider.dto.CommentViewDTO;
import com.name.blog.provider.service.CommentViewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentViewController {
	private final CommentViewService commentViewService;
	
	@GetMapping("/api/v1/post/{post-id}/comment-views")
	public Page<CommentViewDTO> getCommentViewsByPostId(@PathVariable("post-id") Long postId
			, @RequestParam(value="page", defaultValue="0") Integer page) {
		return commentViewService.selectCommentViewListByPostId(postId, page);
	}
}
