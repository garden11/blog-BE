package com.name.blog.constants;

import lombok.Getter;

@Getter
public enum Retentions {
    ACCESS_TOKEN_MINUTES(20L),
    REFRESH_TOKEN_DAYS(1L),
    COMMENT_DAYS(1L);

    private final Long value;

    Retentions(Long value) {
        this.value = value;
    }

}
