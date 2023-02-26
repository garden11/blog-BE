package com.name.blog.provider.security;

import com.name.blog.util.DateUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.UUID;

@Slf4j
public class ProcessToken implements com.name.blog.core.security.ProcessToken {
    @Getter
    private final String token;

    ProcessToken(String token) {
        this.token = token;
    }
}
