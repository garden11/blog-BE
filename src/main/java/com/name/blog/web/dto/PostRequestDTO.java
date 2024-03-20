package com.name.blog.web.dto;

import com.name.blog.core.entity.Post;

import lombok.Data;

import java.util.List;

@Data
public class PostRequestDTO {
	private String username;
	private String title;
	// DB MIDIUMTEXT default value 설정 불가
	private String content = "";
	private String createdAt;
	private String registeredAt;
	private String updatedAt;
	private List<String> imageUris;
	
	public Post toEntity() {
		return Post.builder()
				.username(username)
				.createdAt(Long.valueOf(createdAt))
				.content(content)
				.build();
	}
}
