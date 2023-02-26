package com.name.blog.web;

import com.name.blog.core.security.Auth;
import com.name.blog.core.security.Role;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.name.blog.provider.dto.CommentDTO;
import com.name.blog.provider.service.CommentService;
import com.name.blog.web.dto.CommentRequestDTO;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping
public class CommentController {
	private final CommentService commentService;
	
	@PostMapping("/api/v1/comment")
	@Auth(roles = {Role.USER})
	public CommentDTO createComment(@RequestBody CommentRequestDTO commentRequestDTO) {
		return commentService.insertComment(commentRequestDTO);
	}
	
	@DeleteMapping("/api/v1/comment/{id}")
	@Auth(roles = {Role.USER})
	public CommentDTO removeComment(@PathVariable("id") Long id) {
		return commentService.deleteCommentById(id);
	}
}
