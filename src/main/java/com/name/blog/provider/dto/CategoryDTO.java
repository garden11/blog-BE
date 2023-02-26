package com.name.blog.provider.dto;

import com.name.blog.core.entity.Category;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CategoryDTO {
	private String id;
	private String username;
	private String name;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static CategoryDTO of(Category category) {
        return CategoryDTO.builder()
                .id(responseUtil.convertString(category.getId()))
                .username(category.getUsername())
                .name(category.getName())
                .build();
    }
}
