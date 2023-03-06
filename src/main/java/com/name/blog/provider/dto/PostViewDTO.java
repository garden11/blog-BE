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
				 .id(responseUtil.handleValue(postView.getId()))
				 .username(responseUtil.handleValue(postView.getUsername()))
				 .categoryId(responseUtil.handleValue(postView.getCategoryId()))
				 .categoryName(responseUtil.handleValue(postView.getCategoryName()))
				 .title(responseUtil.handleValue(postView.getTitle()))
				 .content(responseUtil.handleValue(postView.getContent()))
				 .registeredAt(responseUtil.handleValue(postView.getRegisteredAt()))
				 .updatedAt(responseUtil.handleValue(postView.getUpdatedAt()))
				 .thumbnailImageId(responseUtil.handleValue(postView.getThumbnailImageId()))
				 .thumbnailImageUri(responseUtil.handleValue(postView.getThumbnailImageUri()))
				 .build();
	}
}
