package com.name.blog.provider.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RefreshTokenProvider implements com.name.blog.core.security.RefreshTokenProvider {
    @Override
    public RefreshToken createToken() {
        return new RefreshToken();
    }
}
