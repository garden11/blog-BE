package com.name.blog.provider.dto;

import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileDetailDTO {
    private String id;
    private String username;
    private String profileImageId;
    private String profileImageUri;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static ProfileDetailDTO of(ProfileInfo profileInfo) {
        return ProfileDetailDTO.builder()
                .id(responseUtil.handleValue(profileInfo.getId()))
                .username(responseUtil.handleValue(profileInfo.getUsername()))
                .profileImageId(responseUtil.handleValue(profileInfo.getProfileImageId()))
                .profileImageUri(responseUtil.handleValue(profileInfo.getProfileImageUri()))
                .build();
    }
}
