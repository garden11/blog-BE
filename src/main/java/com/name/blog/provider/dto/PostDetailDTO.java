package com.name.blog.provider.dto;

import com.name.blog.core.entity.PostInfo;
import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class PostDetailDTO {
    private String id;
    private String username;
    private String title;
    private String content;
    private String registeredAt;
    private String updatedAt;
    private String thumbnailImageId;
    private String thumbnailImageUri;
    private String profileId;
    private String profileImageId;
    private String profileImageUri;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static PostDetailDTO of(PostInfo postInfo, ProfileInfo profileInfo) {
        return PostDetailDTO.builder()
                .id(responseUtil.handleValue(postInfo.getId()))
                .username(responseUtil.handleValue(postInfo.getUsername()))
                .title(responseUtil.handleValue(postInfo.getTitle()))
                .content(responseUtil.handleValue(postInfo.getContent()))
                .registeredAt(responseUtil.handleValue(postInfo.getRegisteredAt()))
                .updatedAt(responseUtil.handleValue(postInfo.getUpdatedAt()))
                .thumbnailImageId(responseUtil.handleValue(postInfo.getThumbnailImageId()))
                .thumbnailImageUri(responseUtil.handleValue(postInfo.getThumbnailImageUri()))
                .profileId(responseUtil.handleValue(profileInfo.getId()))
                .profileImageId(responseUtil.handleValue(profileInfo.getProfileImageId()))
                .profileImageUri(responseUtil.handleValue(profileInfo.getProfileImageUri()))
                .build();
    }
}
