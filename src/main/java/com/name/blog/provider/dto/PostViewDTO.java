package com.name.blog.provider.dto;

import com.name.blog.core.entity.PostView;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostViewDTO {
	private String id;
	private String username;
	private String categoryId;
	private String categoryName;
	private String title;
	private String content;
	private String registeredAt;
	private String updatedAt;
	private String thumbnailImageId;
	private String thumbnailImageUri;

	private final static ResponseUtil responseUtil = new ResponseUtil();

	public static PostViewDTO of(PostView postView) {
		 return PostViewDTO.builder()
				 .id(responseUtil.convertString(postView.getId()))
				 .username(postView.getUsername())
				 .categoryId(responseUtil.convertString(postView.getCategoryId()))
				 .categoryName(postView.getCategoryName())
				 .title(postView.getTitle())
				 .content(postView.getContent())
				 .registeredAt(responseUtil.convertString(postView.getRegisteredAt()))
				 .updatedAt(responseUtil.convertString(postView.getUpdatedAt()))
				 .thumbnailImageId(responseUtil.convertString(postView.getThumbnailImageId()))
				 .thumbnailImageUri(postView.getThumbnailImageUri())
				 .build();
	}
}
