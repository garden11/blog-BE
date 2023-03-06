package com.name.blog.provider.dto;

import com.name.blog.core.entity.Post;
import com.name.blog.core.entity.ProfileView;
import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ProfileViewDTO {
    private String id;
    private String username;
    private String profileImageId;
    private String profileImageUri;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static ProfileViewDTO of(ProfileView profileView) {
        return ProfileViewDTO.builder()
                .id(responseUtil.handleValue(profileView.getId()))
                .username(responseUtil.handleValue(profileView.getUsername()))
                .profileImageId(responseUtil.handleValue(profileView.getProfileImageId()))
                .profileImageUri(responseUtil.handleValue(profileView.getProfileImageUri()))
                .build();
    }
}
