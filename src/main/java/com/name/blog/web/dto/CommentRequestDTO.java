package com.name.blog.web.dto;

import com.name.blog.core.entity.Comment;

import lombok.Data;

@Data
public class CommentRequestDTO {
	private String username;
	private String postId;
	private String content;
	private String registeredAt;
	
	public Comment toEntity() {
		return Comment.builder()
				.postId(Long.valueOf(postId))
				.username(username)
				.content(content)
				.registeredAt(Long.valueOf(registeredAt))
				.build();
	}
}
