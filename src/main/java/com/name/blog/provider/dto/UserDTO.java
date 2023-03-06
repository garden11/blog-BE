package com.name.blog.provider.dto;

import com.name.blog.core.entity.User;
import com.name.blog.core.security.Role;

import com.name.blog.util.ResponseUtil;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
	private String email;
	private String username;
	private String role;

    private final static ResponseUtil responseUtil = new ResponseUtil();

    public static UserDTO of(User user) {
        return UserDTO.builder()
                .username(responseUtil.handleValue(user.getUsername()))
                .email(responseUtil.handleValue(user.getEmail()))
                .role(responseUtil.handleValue(user.getRole()))
                .build();
    }
}
