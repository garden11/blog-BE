package com.name.blog.provider.dto;

import com.name.blog.core.entity.PostTagInfo;
import com.name.blog.core.entity.Tag;
import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TagDTO {
    private String id;
    private String name;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static TagDTO of(Tag tag) {
        return TagDTO.builder()
                .id(responseUtil.handleValue(tag.getId()))
                .name(responseUtil.handleValue(tag.getName()))
                .build();
    }
}
