package com.name.blog.web.dto;

import com.name.blog.core.entity.Category;
import com.name.blog.core.entity.User;

import lombok.Data;

@Data
public class CategoryRequestDTO {
	private String username;
	private String name;
	
	public Category toEntity() {
	    return Category.builder()
	            .username(username)
	            .name(name)
	            .build();
	   }
}
