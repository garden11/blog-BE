package com.name.blog.web.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProfileImageRequestDTO {
	private MultipartFile image;
	private String profileId;
}
