package com.name.blog.provider.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ProcessTokenProvider implements com.name.blog.core.security.ProcessTokenProvider {
    @Override
    public ProcessToken convertToToken(String token) {
        return new ProcessToken(token);
    }
}
