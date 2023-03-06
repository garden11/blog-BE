package com.name.blog.provider.dto;

import com.name.blog.core.entity.ProfileImage;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileImageDTO {
	public String id;
	public String uri;
	public String name;

	private final static ResponseUtil responseUtil = new ResponseUtil();

	public static ProfileImageDTO of(ProfileImage profileImage) {
		 return ProfileImageDTO.builder()
				 .id(responseUtil.handleValue(profileImage.getId()))
				 .uri(responseUtil.handleValue(profileImage.getUri()))
				 .name(responseUtil.handleValue(profileImage.getName()))
				 .build();
	}
}
