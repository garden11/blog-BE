package com.name.blog.provider.dto;

import com.name.blog.core.entity.User;
import com.name.blog.core.security.Role;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDTO {
	private String email;
	private String username;
	private Role role;
	
    public static UserDTO of(User user) {
        return UserDTO.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .role(Role.of(user.getRole()))
                .build();
    }
}
