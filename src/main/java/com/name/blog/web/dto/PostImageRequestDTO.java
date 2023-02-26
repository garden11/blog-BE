package com.name.blog.web.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

import java.util.List;

@Data
public class PostImageRequestDTO {
	private MultipartFile image;
	private Long postId;
	private List<String> uriList;
}
