package com.name.blog.provider.dto;

import com.name.blog.core.entity.PostTagInfo;
import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostTagDetailDTO {
    private String id;
    private String name;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static PostTagDetailDTO of(PostTagInfo postTagInfo) {
        return PostTagDetailDTO.builder()
                .id(responseUtil.handleValue(postTagInfo.getTagId()))
                .name(responseUtil.handleValue(postTagInfo.getTagName()))
                .build();
    }
}
