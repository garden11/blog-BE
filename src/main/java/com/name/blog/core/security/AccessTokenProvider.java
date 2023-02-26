package com.name.blog.core.security;

import com.name.blog.provider.security.AccessToken;

public interface AccessTokenProvider {
    AccessToken createToken(String id, Role role);
    AccessToken convertToToken(String token);
}