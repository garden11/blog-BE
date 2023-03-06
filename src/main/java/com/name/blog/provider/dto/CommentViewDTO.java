package com.name.blog.provider.dto;

import com.name.blog.core.entity.CommentView;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentViewDTO {
	private String id;
	private String postId;
	private String username;
	private String content;
	private String registeredAt;
	private String profileId;
	private String profileImageId;
	private String profileImageUri;

	private final static ResponseUtil responseUtil = new ResponseUtil();

	public static CommentViewDTO of(CommentView commentView) {
		 return CommentViewDTO.builder()
				 .id(responseUtil.handleValue(commentView.getId()))
				 .postId(responseUtil.handleValue(commentView.getPostId()))
				 .username(responseUtil.handleValue(commentView.getUsername()))
				 .content(responseUtil.handleValue(commentView.getContent()))
				 .registeredAt(responseUtil.handleValue(commentView.getRegisteredAt()))
				 .profileId(responseUtil.handleValue(commentView.getProfileId()))
				 .profileImageId(responseUtil.handleValue(commentView.getProfileImageId()))
				 .profileImageUri(responseUtil.handleValue(commentView.getProfileImageUri()))
				 .build();
	}
}
