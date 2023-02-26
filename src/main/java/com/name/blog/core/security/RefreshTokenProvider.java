package com.name.blog.core.security;

import com.name.blog.provider.security.RefreshToken;

public interface RefreshTokenProvider {
    RefreshToken createToken();
}
