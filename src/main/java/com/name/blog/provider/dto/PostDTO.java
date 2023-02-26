package com.name.blog.provider.dto;

import com.name.blog.core.entity.Post;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDTO {
	private String id;
	private String categoryId;
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
                .id(responseUtil.convertString(post.getId()))
                .categoryId(responseUtil.convertString(post.getCategoryId()))
                .username(post.getUsername())
                .title(post.getTitle())
                .content(post.getContent())
				.createdAt(responseUtil.convertString(post.getCreatedAt()))
				.registeredAt(responseUtil.convertString(post.getRegisteredAt()))
				.updatedAt(responseUtil.convertString(post.getUpdatedAt()))
				.registerYN(post.getRegisterYN())
                .build();
    }
}
