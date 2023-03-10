package com.name.blog.provider.dto;

import com.name.blog.core.entity.PostImage;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostImageDTO {
	private String id;
	private String uri;
	private String name;

	private final static ResponseUtil responseUtil = new ResponseUtil();

	public static PostImageDTO of(PostImage postImage) {
		 return PostImageDTO.builder()
				 .id(responseUtil.handleValue(postImage.getId()))
				 .uri(responseUtil.handleValue(postImage.getUri()))
				 .name(responseUtil.handleValue(postImage.getName()))
				 .build();
	}
}
