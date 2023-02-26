package com.name.blog.core.security;

public interface ProcessTokenProvider {
    ProcessToken convertToToken(String token);
}
