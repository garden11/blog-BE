package com.name.blog.web.dto;

import lombok.Data;

@Data
public class ResetPasswordRequestDTO {
    private String processToken;
    private String newPassword;
}
