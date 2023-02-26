package com.name.blog.provider.security;

import com.name.blog.core.security.Role;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;

import java.security.Key;

@Slf4j
public class AccessTokenProvider implements com.name.blog.core.security.AccessTokenProvider {
    private final Key key;

    public AccessTokenProvider(String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    @Override
    public AccessToken createToken(String id, Role role) {
        return new AccessToken(id, role, key);
    }

    @Override
    public AccessToken convertToToken(String token) {
        return new AccessToken(token, key);
    }
}