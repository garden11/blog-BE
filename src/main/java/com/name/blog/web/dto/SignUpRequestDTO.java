package com.name.blog.web.dto;

import com.name.blog.core.entity.User;
import com.name.blog.core.security.Role;

import lombok.Data;

@Data
public class SignUpRequestDTO {
	private String email;
	private String password;
	private String username;
	private final Role role = Role.USER;
	
	public User toEntity() {
	    return User.builder()
	            .email(email)
	            .password(password)
	            .username(username)
	            .role(role)
	            .build();
	   }
}
