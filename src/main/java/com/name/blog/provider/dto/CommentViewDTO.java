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
				 .id(responseUtil.convertString(commentView.getId()))
				 .postId(responseUtil.convertString(commentView.getPostId()))
				 .username(commentView.getUsername())
				 .content(commentView.getContent())
				 .registeredAt(responseUtil.convertString(commentView.getRegisteredAt()))
				 .profileId(responseUtil.convertString(commentView.getProfileId()))
				 .profileImageId(responseUtil.convertString(commentView.getProfileImageId()))
				 .profileImageUri(commentView.getProfileImageUri())
				 .build();
	}
}
