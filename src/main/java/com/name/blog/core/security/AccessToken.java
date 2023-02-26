package com.name.blog.core.security;

import io.jsonwebtoken.Claims;

public interface AccessToken {
    boolean validate();
    Claims getData();
}