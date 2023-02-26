package com.name.blog.web.dto;

import lombok.Data;

@Data
public class ReissueAuthTokensRequestDTO {
    private String refreshToken;
}
