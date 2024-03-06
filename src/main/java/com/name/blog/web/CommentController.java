package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import com.name.blog.provider.dto.CommentDetailDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.name.blog.provider.dto.CommentDTO;
import com.name.blog.provider.service.CommentService;
import com.name.blog.web.dto.CommentRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentController {
	private final CommentService commentService;

	@GetMapping("/api/v1/post/{post-id}/comment-details")
	public Page<CommentDetailDTO> getCommentDetailsByPostId(@PathVariable("post-id") Long postId
			, @RequestParam(value="page", defaultValue="0") Integer page) {
		return commentService.selectCommentDetailListByPostId(postId, page);
	}

	@PostMapping("/api/v1/comment")
	@Auth(roles = {Role.USER})
	public CommentDTO createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
		return commentService.insertComment(commentRequestDTO);
	}
	
	@DeleteMapping("/api/v1/comment/{id}")
	@Auth(roles = {Role.USER})
	public void removeComment(@PathVariable("id") Long id) {
		commentService.deleteCommentById(id);
	}
}
