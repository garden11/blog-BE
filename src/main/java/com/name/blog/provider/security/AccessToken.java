package com.name.blog.provider.security;

import java.security.Key;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.name.blog.core.security.Role;
import com.name.blog.exception.CustomAccessTokenRuntimeException;

import com.name.blog.util.DateUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SecurityException;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AccessToken implements com.name.blog.core.security.AccessToken {
    private static final long TOKEN_RETENTION_MINUTES = 20;
    private static final String AUTHORITIES_KEY = "role";

    private final DateUtil dateUtil= new DateUtil();

    @Getter
    private final String token;
    @Getter
    private final Date expiredDate;
    private final Key key;

    AccessToken(String token, Key key) {
        this.key = key;
        this.expiredDate = createExpiredDate();
        this.token = token;
    }

    AccessToken(String id, Role role, Key key) {
        this.key = key;
        this.expiredDate = createExpiredDate();
        this.token = createToken(id, role);
    }

    @Override
    public boolean validate() {
        return getData() != null;
    }

    @Override
    public Claims getData() {
        try {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        } catch (SecurityException e) {
            log.info("Invalid JWT signature.");
            throw new CustomAccessTokenRuntimeException();
        } catch (MalformedJwtException e) {
            log.info("Invalid JWT token.");
            throw new CustomAccessTokenRuntimeException();
        } catch (ExpiredJwtException e) {
            log.info("Expired JWT token.");
            throw new CustomAccessTokenRuntimeException();
        } catch (UnsupportedJwtException e) {
            log.info("Unsupported JWT token.");
            throw new CustomAccessTokenRuntimeException();
        } catch (IllegalArgumentException e) {
            log.info("JWT token compact of handler are invalid.");
            throw new CustomAccessTokenRuntimeException();
        }
    }

    private String createToken(String id, Role role) {
        String token = Jwts.builder()
                .setSubject(id)
                .claim(AUTHORITIES_KEY, role.getCode())
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(expiredDate)
                .compact();

        return token;
    }



    private Date createExpiredDate() {
        return dateUtil.createUTCDatePlus(TOKEN_RETENTION_MINUTES, ChronoUnit.MINUTES);
    }
}
