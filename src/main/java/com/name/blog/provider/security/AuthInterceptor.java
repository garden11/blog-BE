package com.name.blog.provider.security;

import java.util.Arrays;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.name.blog.core.security.Auth;
import com.name.blog.exception.CustomAccessTokenRuntimeException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.name.blog.core.security.Role;
import com.name.blog.exception.CustomAuthenticationException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {
    private final AccessTokenProvider accessTokenProvider;
    private static final String AUTHORIZATION_HEADER_KEY = "x-auth-token";

    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object handler) {

		Optional<String> token = resolveToken(servletRequest);

        if(!(handler instanceof HandlerMethod)){
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Auth auth = handlerMethod.getMethodAnnotation(Auth.class);

        if (auth == null) {
            return true;
        } else {
            return checkSecurity(token, auth.roles());
        }
    }

    private boolean checkSecurity(Optional<String>optionalToken, Role[] roles) {
        if (!(optionalToken.isPresent())) {
            throw new CustomAccessTokenRuntimeException();
        }

        AccessToken accessToken = accessTokenProvider.convertToToken(optionalToken.get());

        if(!(Arrays.asList(roles).contains(Role.of((String) accessToken.getData().get("role"))))) {
            throw new CustomAuthenticationException();
        }

        if(!(accessToken.validate())) {
            throw new CustomAccessTokenRuntimeException();
        }

        return true;
    }

    private Optional<String> resolveToken(HttpServletRequest request) {
        String token = request.getHeader(AUTHORIZATION_HEADER_KEY);

        if (StringUtils.hasText(token)) {
            return Optional.of(token);
        } else {
            return Optional.empty();
        }
    }
}
