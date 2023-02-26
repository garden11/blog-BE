package com.name.blog.web.dto;

import lombok.Data;

@Data
public class UpdatePasswordRequestDTO {
    private String username;
    private String password;
    private String newPassword;
}
