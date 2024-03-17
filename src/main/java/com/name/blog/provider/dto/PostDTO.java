package com.name.blog.provider.dto;

import com.name.blog.core.entity.Post;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDTO {
	private String id;
	private String username;
	private String title;
	private String content;
	private String createdAt;
	private String registeredAt;
	private String updatedAt;
	private String registerYN;

	private final static ResponseUtil responseUtil = new ResponseUtil();
	
    public static PostDTO of(Post post) {
        return PostDTO.builder()
                .id(responseUtil.handleValue(post.getId()))
                .username(responseUtil.handleValue(post.getUsername()))
                .title(responseUtil.handleValue(post.getTitle()))
                .content(responseUtil.handleValue(post.getContent()))
				.createdAt(responseUtil.handleValue(post.getCreatedAt()))
				.registeredAt(responseUtil.handleValue(post.getRegisteredAt()))
				.updatedAt(responseUtil.handleValue(post.getUpdatedAt()))
				.registerYN(responseUtil.handleValue(post.getRegisterYN()))
                .build();
    }
}
