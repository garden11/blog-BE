package com.name.blog.provider.dto;

import com.name.blog.core.entity.Comment;
import com.name.blog.core.entity.ProfileInfo;
import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CommentDetailDTO {
    private String id;
    private String postId;
    private String username;
    private String content;
    private String registeredAt;
    private String profileId;
    private String profileImageId;
    private String profileImageUri;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static CommentDetailDTO of(Comment comment, ProfileInfo profileInfo) {
        return CommentDetailDTO.builder()
                .id(responseUtil.handleValue(comment.getId()))
                .postId(responseUtil.handleValue(comment.getPostId()))
                .username(responseUtil.handleValue(comment.getUsername()))
                .content(responseUtil.handleValue(comment.getContent()))
                .registeredAt(responseUtil.handleValue(comment.getRegisteredAt()))
                .profileId(responseUtil.handleValue(profileInfo.getId()))
                .profileImageId(responseUtil.handleValue(profileInfo.getProfileImageId()))
                .profileImageUri(responseUtil.handleValue(profileInfo.getProfileImageUri()))
                .build();
    }
}
