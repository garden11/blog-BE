package com.name.blog.provider.security;

import com.name.blog.util.DateUtil;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Slf4j
public class RefreshToken implements com.name.blog.core.security.RefreshToken {
    @Getter
    private final String token;

    RefreshToken() {
        this.token = createToken();
    }

    RefreshToken(String token) {
        this.token = token;
    }

    private String createToken() {
        var token = UUID.randomUUID().toString();

        return token;
    }
}
