package com.name.blog.exception;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ExceptionResponse {
    private String message;
    private int status;
    private String code;
}
