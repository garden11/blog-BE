package com.name.blog.provider.dto;

import com.name.blog.core.entity.Comment;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDTO {
	private String id;
	private String postId;
	private String username;
	private String content;
	private String registeredAt;

	private final static ResponseUtil responseUtil = new ResponseUtil();

	public static CommentDTO of(Comment comment) {
	    return CommentDTO.builder()
				.id(responseUtil.convertString(comment.getId()))
	    		.postId(responseUtil.convertString(comment.getPostId()))
	            .username(comment.getUsername())
	            .content(comment.getContent())
	            .registeredAt(responseUtil.convertString(comment.getRegisteredAt()))
	            .build();
	}
}
